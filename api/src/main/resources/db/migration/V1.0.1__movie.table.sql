CREATE TABLE public.movie
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR NOT NULL,
    platform    VARCHAR NOT NULL,
    studio      VARCHAR NOT NULL,
    score       ENUM('dislike', 'like','love')
    /*cover*/
);

ALTER SEQUENCE movie_id_seq RESTART 100000 INCREMENT BY 50;
