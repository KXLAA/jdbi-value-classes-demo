package com.library.demo.entity;

import com.library.demo.BaseUUID;

import java.util.UUID;

public class BookId extends BaseUUID {
    public BookId() { super(); }
    public BookId(UUID id) { super(id); }
    public BookId(String id) { super(id); }

    public static BookId of(String id) { return new BookId(id); }
    public static BookId of(UUID id) { return new BookId(id); }
}
