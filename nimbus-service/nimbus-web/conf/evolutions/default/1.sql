# --- !Ups

CREATE TABLE IF NOT EXISTS status (
    app_name varchar(32) NOT NULL,
    task_name varchar(32) NOT NULL,
    task_status varchar(32) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY(app_name, task_name)
    );

# --- !Downs

DROP TABLE IF EXISTS status;
