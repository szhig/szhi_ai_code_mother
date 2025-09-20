package com.szhig.szhigaicodemather.model.enums;

import cn.hutool.core.util.ObjUtil;
import com.szhig.szhigaicodemather.model.entity.ChatHistory;
import lombok.Getter;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.model.enums
 * @Author: shizhiguang
 * @CreateTime: 2025-09-16  11:02
 * @Description: TODO
 * @Version: 1.0
 */
@Getter
public enum ChatHistoryMessageTypeEnum {

    USER("用户", "user"),
    AI("AI", "ai");

    private final String text;

    private final String value;

    ChatHistoryMessageTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static ChatHistoryMessageTypeEnum getEnumByValue(String value) {
        if (ObjUtil.isEmpty(value)) return null;
        for (ChatHistoryMessageTypeEnum valueEnum : ChatHistoryMessageTypeEnum.values()) {
            if (valueEnum.value.equals(value)) {
                return valueEnum;
            }
        }
        return null;
    }
}
