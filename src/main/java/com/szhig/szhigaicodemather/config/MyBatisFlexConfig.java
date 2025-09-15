package com.szhig.szhigaicodemather.config;

import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.audit.ConsoleMessageCollector;
import com.mybatisflex.core.audit.MessageCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.config
 * @Author: shizhiguang
 * @CreateTime: 2025-09-14  15:21
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class MyBatisFlexConfig {

    private static final Logger logger = LoggerFactory.getLogger("mybatis-flex-sql");

    public MyBatisFlexConfig() {
        // 开启审计功能
        AuditManager.setAuditEnable(true);

        // 设置SQL审计收集器
        MessageCollector collector = new ConsoleMessageCollector();
        AuditManager.setMessageCollector(collector);
    }
}
