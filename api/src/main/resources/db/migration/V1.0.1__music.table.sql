CREATE TABLE public.music
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR NOT NULL,
    artist    VARCHAR NOT NULL,
    /*cover*/
);

ALTER SEQUENCE music_id_seq RESTART 100000 INCREMENT BY 50;
