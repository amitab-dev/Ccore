CREATE TABLE users
(
    id         UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    full_name  VARCHAR(254)     NOT NULL,
    email      TEXT             NOT NULL UNIQUE,
    password   TEXT             NOT NULL,
    avatar     TEXT             NOT NULL DEFAULT '',
    created_at TIMESTAMPTZ      NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ      NOT NULL DEFAULT NOW()
);