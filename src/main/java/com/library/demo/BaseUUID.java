package com.library.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public abstract class BaseUUID {
    private final UUID value;

    protected BaseUUID(UUID value) {
        this.value = Objects.requireNonNull(value, "ID cannot be null");
    }

    @JsonCreator
    protected BaseUUID(String id) {
        this(UUID.fromString(id));
    }

    protected BaseUUID() {
        this(UUID.randomUUID());
    }

    @JsonValue
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseUUID baseId = (BaseUUID) o;
        return Objects.equals(value, baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
