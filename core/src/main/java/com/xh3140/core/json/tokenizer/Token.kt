package com.xh3140.core.json.tokenizer


/**
 * @Author : xh3140
 * @Time : 2020/7/11 22:14
 * @File : Token.kt
 */

class Token(val type: Type, val value: String) {
    // 单词类型
    enum class Type(val code: Int) {
        BEGIN_OBJECT(1),                // {
        END_OBJECT(2),                  // }
        BEGIN_ARRAY(4),                 // [
        END_ARRAY(8),                   // ]
        NULL(16),                       // null
        NUMBER(32),                     // number
        STRING(64),                     // string
        BOOLEAN(128),                   // boolean
        SEP_COLON(256),                 // :
        SEP_COMMA(512),                 // ,
        END_JSON(1024);                 // end json
    }
}