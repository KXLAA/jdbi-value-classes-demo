package com.library.demo.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookCreateRequest(
        @NotNull
        String title,

        @NotNull
        String author,

        @NotNull
        String publisher,

        @NotNull
        String isbn,

        LocalDateTime publishedDate
) {}
