CREATE TABLE IF NOT EXISTS tbl_members (
    id BIGINT auto_increment,
    nick_name varchar(255) unique,
    created_at datetime,
    updated_at datetime,

    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT UQ_MEMEBER_NICK_NAME UNIQUE (nick_name)
);