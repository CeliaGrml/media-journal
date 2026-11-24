CREATE TABLE public.book
(
    id      BIGSERIAL PRIMARY KEY,
    title   VARCHAR NOT NULL,
    author  VARCHAR NOT NULL,
    genre   VARCHAR NOT NULL,
    trope   VARCHAR NOT NULL ,
    support ENUM('physical', 'kindle'),
    score   ENUM('dislike', 'like','love'),
    spicy   INT BETWEEN 0 AND 3
    /*cover*/
);

ALTER SEQUENCE book_id_seq RESTART 100000 INCREMENT BY 50;
