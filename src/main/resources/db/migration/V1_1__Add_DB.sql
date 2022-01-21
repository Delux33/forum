CREATE TABLE usr
(
    id              BIGSERIAL    NOT NULL,
    activation_code VARCHAR(255),
    active          BOOLEAN      NOT NULL,
    email           VARCHAR(255),
    password        VARCHAR(255) NOT NULL,
    username        VARCHAR(255) NOT NULL,
    phone_number    VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    user_id INT8 NOT NULL,
    roles   VARCHAR(255)
);

CREATE TABLE message
(
    id      BIGSERIAL    NOT NULL,
    text    VARCHAR(255) NOT NULL,
    user_id INT8         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE article
(
    id           BIGSERIAL    NOT NULL,
    text_article VARCHAR(2048),
    title        VARCHAR(255) NOT NULL,
    user_id      INT8         NOT NULL,
    message_id   INT8         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE comment
(
    id         BIGSERIAL     NOT NULL,
    comment    VARCHAR(2048) NOT NULL,
    article_id INT8          NOT NULL,
    user_id    INT8          NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE user_role
    ADD CONSTRAINT user_role_user_fk
        FOREIGN KEY (user_id) REFERENCES usr (id);

ALTER TABLE message
    ADD CONSTRAINT message_user_fk
        FOREIGN KEY (user_id) REFERENCES usr (id);

ALTER TABLE article
    ADD CONSTRAINT article_user_fk
        FOREIGN KEY (user_id) REFERENCES usr (id);

ALTER TABLE article
    ADD CONSTRAINT article_message_fk
        FOREIGN KEY (message_id) REFERENCES message (id);

ALTER TABLE comment
    ADD CONSTRAINT comment_user_fk
        FOREIGN KEY (user_id) REFERENCES usr (id);

ALTER TABLE comment
    ADD CONSTRAINT comment_article_fk
        FOREIGN KEY (article_id) REFERENCES article (id);