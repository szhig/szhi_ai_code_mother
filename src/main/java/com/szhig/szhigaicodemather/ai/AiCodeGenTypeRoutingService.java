package com.szhig.szhigaicodemather.ai;

import com.szhig.szhigaicodemather.model.enums.CodeGenTypeEnum;
import dev.langchain4j.service.SystemMessage;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.ai
 * @Author: shizhiguang
 * @CreateTime: 2025-09-23  17:29
 * @Description: TODO
 * @Version: 1.0
 */
public interface AiCodeGenTypeRoutingService {

    @SystemMessage(fromResource = "prompt/codegen-routing-system-prompt.txt")
    CodeGenTypeEnum routeCodeGenType(String userPrompt);
}
