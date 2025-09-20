package com.szhig.szhigaicodemather.generator;

import cn.hutool.core.lang.Dict;
import cn.hutool.setting.yaml.YamlUtil;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Map;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.generator
 * @Author: shizhiguang
 * @CreateTime: 2025-09-13  20:44
 * @Description: TODO
 * @Version: 1.0
 */
public class MybatisCodeGenerator {

    private static final String[] TABLE_NAMES = {"chat_history"};

    public static void main(String[] args) {
        // 获取数据源信息
        Dict dict = YamlUtil.loadByPath("application.yml");
        Map<String, Object> dataSourceConfig = dict.getByPath("spring.datasource");
        String url = dataSourceConfig.get("url").toString();
        String username = dataSourceConfig.get("username").toString();
        String password = dataSourceConfig.get("password").toString();
        // 配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // 创建配置内容
        GlobalConfig globalConfig = createGlobalConfig();
        Generator generator = new Generator(dataSource, globalConfig);

        // 生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();

        // 设置根包
        globalConfig.getPackageConfig()
                .setBasePackage("com.szhig.szhigaicodemather.genresult");

        // 设置表前缀和生成哪些表
        globalConfig.getStrategyConfig()
                .setGenerateTable(TABLE_NAMES)
                .setLogicDeleteColumn("isDelete");

        // 设置生成entity并启用lombok
        globalConfig.enableEntity()
                .setWithLombok(true)
                .setJdkVersion(17);

        // 设置生成mapper
        globalConfig.enableMapper();
        globalConfig.enableMapperXml();

        // 设置生成Service
        globalConfig.enableService();
        globalConfig.enableServiceImpl();

        // 设置生成controller
        globalConfig.enableController();

        // 设置生成时间和字符串为空，避免多余的代码改动
        globalConfig.getJavadocConfig()
                .setAuthor("<a href=\"https://github.com/szhig\"></a>")
                .setSince("");

        return globalConfig;
    }
}
