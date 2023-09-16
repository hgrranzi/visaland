package com.hgrranzi.visaland.persistence.entity;

public enum AppStatus {
    OPEN,
    PROCESSING,
    ACCEPTED,
    REJECTED;

    public boolean isFinal() {
        return this == ACCEPTED || this == REJECTED;
    }
}
