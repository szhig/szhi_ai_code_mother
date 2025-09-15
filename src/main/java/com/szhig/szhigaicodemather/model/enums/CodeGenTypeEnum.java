package com.szhig.szhigaicodemather.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

@Getter
public enum CodeGenTypeEnum {
    HTML("原生HTML模式", "html"),
    MULTI_FILE("原生多文件模式", "multi_file");

    private final String text;
    private final String value;

    CodeGenTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据value获取枚举
     *
     * @param value 枚举值的value
     * @return 枚举值
     */
    public static CodeGenTypeEnum getByValue(String value) {
        if (ObjUtil.isEmpty(value)) return null;
        for (CodeGenTypeEnum item : CodeGenTypeEnum.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return null;
    }
}
