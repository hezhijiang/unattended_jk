package com.gez.woodware.entity.basics;

import lombok.Data;

@Data
public class CodeValue {
    /**
     * 码.
     */
    private String code;

    /**
     * 值.
     */
    private String value;


    public CodeValue(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public String getValue() {
        if (value == null || value == "" || value.length() < 1) {
            value = "空";
        }
        return value;
    }

}
