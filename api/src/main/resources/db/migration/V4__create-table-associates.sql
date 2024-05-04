create table associates
(
    id   BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(250)                 NOT NULL,
    cpf  VARCHAR(11)                  NOT NULL
);
