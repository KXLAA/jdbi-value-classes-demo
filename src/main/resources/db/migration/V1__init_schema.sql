CREATE TABLE "library" (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR
);

CREATE TABLE "book" (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR NOT NULL,
    author VARCHAR NOT NULL,
    publisher VARCHAR,
    isbn VARCHAR NOT NULL,
    published_date TIMESTAMPTZ
);

CREATE TABLE "book_in_library" (
    library_id UUID NOT NULL,
    book_id UUID NOT NULL,

    PRIMARY KEY (library_id, book_id),
    FOREIGN KEY (library_id) REFERENCES library(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES  book(id) ON DELETE CASCADE
);
