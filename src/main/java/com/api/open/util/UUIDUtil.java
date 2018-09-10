package com.api.open.util;

import java.util.UUID;

public class UUIDUtil {
    public UUIDUtil() {
    }

    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     *
     * @return 返回32位UUID，生成通用id
     * @Author Beldon
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

