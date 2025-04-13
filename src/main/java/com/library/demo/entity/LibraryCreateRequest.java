package com.library.demo.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LibraryCreateRequest(@NotNull String name, String description) { }
