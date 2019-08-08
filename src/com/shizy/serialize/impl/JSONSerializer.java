package com.shizy.serialize.impl;

import com.google.gson.Gson;
import com.shizy.serialize.Serializer;
import com.shizy.serialize.SerializerAlogrithm;
import io.netty.util.CharsetUtil;

public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        if (object == null) {
            return new byte[0];
        }
        return new Gson().toJson(object).getBytes(CharsetUtil.UTF_8);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        String json = new String(bytes, CharsetUtil.UTF_8);
        return new Gson().fromJson(json, clazz);
    }
}
