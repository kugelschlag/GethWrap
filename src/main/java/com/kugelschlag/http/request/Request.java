package com.kugelschlag.http.request;

import java.util.HashMap;
import java.util.Map;

public class Request {

//    private static final String ACTION_KEY = "action";
    private final String action;
    private final Map<String, Object> map;

    private Request(String keyType, String action, Map<String, Object> map) {
        this.action = action;
        this.map = map;
        this.map.put(keyType, action);
//        this.map.put(ACTION_KEY, action);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public String getAction() {
        return action;
    }

    public static Builder action(String keyType, String action) {
        return new Builder(keyType, action);
    }

    public static class Builder {

        private final String keyType;
        private final String action;
        private final Map<String, Object> map;

        Builder(String keyType, String action) {
            this.keyType = keyType;
            this.action = action;
            this.map = new HashMap<>();
        }

        public Builder param(String key, Object value) {
            map.put(key, value);
            return this;
        }

        public Request build() {
            return new Request(keyType, action, map);
        }
    }
}
