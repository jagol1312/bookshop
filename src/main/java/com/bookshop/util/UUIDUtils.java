package com.bookshop.util;

import java.util.UUID;
//使用UUID来对图片名称进行重新生成。
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
