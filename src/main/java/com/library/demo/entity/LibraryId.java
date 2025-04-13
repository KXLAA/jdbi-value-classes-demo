package com.library.demo.entity;

import com.library.demo.BaseUUID;

import java.util.UUID;

public class LibraryId extends BaseUUID {
    public LibraryId() { super(); }
    public LibraryId(UUID id) { super(id); }
    public LibraryId(String id) { super(id); }

    public static LibraryId of(String id) { return new LibraryId(id); }
    public static LibraryId of(UUID id) { return new LibraryId(id); }
}