package com.szhig.szhigaicodemather.ai.model.message;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.ai.model.message
 * @Author: shizhiguang
 * @CreateTime: 2025-09-20  19:49
 * @Description: TODO
 * @Version: 1.0
 */

import dev.langchain4j.service.tool.ToolExecution;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 工具执行结果消息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ToolExecutedMessage extends StreamMessage {

    private String id;

    private String name;

    private String arguments;

    private String result;

    public ToolExecutedMessage(ToolExecution toolExecution) {
        super(StreamMessageTypeEnum.TOOL_EXECUTED.getValue());
        this.id = toolExecution.request().id();
        this.name = toolExecution.request().name();
        this.arguments = toolExecution.request().arguments();
        this.result = toolExecution.result();
    }
}

