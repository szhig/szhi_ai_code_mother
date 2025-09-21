package com.szhig.szhigaicodemather.ai.model.message;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.ai.model.message
 * @Author: shizhiguang
 * @CreateTime: 2025-09-20  19:46
 * @Description: TODO
 * @Version: 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流式消息响应基类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamMessage {
    private String type;
}

