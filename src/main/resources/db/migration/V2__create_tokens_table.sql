CREATE TABLE tokens
(
    id            UUID PRIMARY KEY                             NOT NULL DEFAULT gen_random_uuid(),
    user_id       UUID REFERENCES users (id) ON DELETE CASCADE NOT NULL,
    refresh_token TEXT                                         NOT NULL UNIQUE,
    revoked_at    TIMESTAMPTZ,
    expires_at    TIMESTAMPTZ                                  NOT NULL,
    created_at    TIMESTAMPTZ                                  NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMPTZ                                  NOT NULL DEFAULT NOW()
);