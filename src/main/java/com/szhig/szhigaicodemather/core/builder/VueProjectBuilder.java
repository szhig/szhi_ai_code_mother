package com.szhig.szhigaicodemather.core.builder;

import cn.hutool.core.util.RuntimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.core.builder
 * @Author: shizhiguang
 * @CreateTime: 2025-09-20  21:38
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
public class VueProjectBuilder {

    // 执行npm install命令
    private boolean executeNpmInstall(File projectDir) {
        log.info("执行 npm install...");
        String command = String.format("%s install", buildCommand("npm"));
        return executeCommand(projectDir, command, 300); // 5分钟超时
    }

    // 执行 npm run build命令
    private boolean executeNpmBuild(File projectDir) {
        log.info("执行 npm run build...");
        String command = String.format("%s run build", buildCommand("npm"));
        return executeCommand(projectDir, command, 180);  // 3分钟超时
    }

    // Window系统检测
    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    // 根据操作系统构造命令
    private String buildCommand(String baseCommand) {
        if (isWindows()) {
            return baseCommand + ".cmd";
        }
        return baseCommand;
    }

    private boolean executeCommand (File workingDir, String command, int timeoutSeconds) {
        try {
            log.info("在目录 {} 中执行命令: {}", workingDir.getAbsolutePath(), command);
            Process process = RuntimeUtil.exec(
                    null,
                    workingDir,
                    command.split("\\s+"));

            // 等待进程完成，设置超时
            boolean finished = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);
            if (!finished) {
                log.error("命令执行超时（{}秒），强制终止进程",timeoutSeconds);
                process.destroyForcibly();
                return false;
            }
            int exitCode = process.exitValue();
            if (exitCode == 0) {
                log.info("命令执行成功：{}", command);
                return true;
            } else {
                log.error("命令执行失败，退出码：{}", exitCode);
                return false;
            }
        } catch (Exception e) {
            log.error("执行命令失败：{}，错误信息：{}", command, e.getMessage());
            return false;
        }
    }

    // 构建Vue项目
    public boolean buildProject(String projectPath) {
        File projectDir = new File(projectPath);
        if (!projectDir.exists() || !projectDir.isDirectory()) {
            log.error("项目目录不存在：{}", projectPath);
            return false;
        }

        // 检查package.json是否存在
        File packageJson = new File(projectDir, "package.json");
        if (!packageJson.exists()) {
            log.error("package.json文件不存在: {}", packageJson.getAbsolutePath());
            return false;
        }

        log.info("开始构建 Vue 项目: {}", projectPath);
        // 执行npm install
        if (!executeNpmInstall(projectDir)) {
            log.error("npm install 执行失败");
            return false;
        }
        // 执行npm run build
        if (!executeNpmBuild(projectDir)) {
            log.error("npm run build 执行失败");
            return false;
        }
        // 验证 dist 目录是否生成
        File distDir = new File(projectDir, "dist");
        if (!distDir.exists()) {
            log.error("构建完成但 dist 目录未生成: {}", distDir.getAbsolutePath());
            return false;
        }
        log.info("Vue 项目构建成功, dist 目录：{}", distDir.getAbsolutePath());
        return true;
    }

    // 异步构建Vue项目 -- 使用jdk21的虚拟线程  -- 但是我用的是jdk17

}
