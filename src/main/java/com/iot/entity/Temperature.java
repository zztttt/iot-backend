package com.iot.entity;

public class Temperature {
    private final long timestamp;
    private final long data;

    public Temperature(long timestamp, long data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getData() {
        return data;
    }
}
