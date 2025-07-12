-- V1__initial_schema.sql
CREATE TABLE role (
    role_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by VARCHAR(255),
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    updated_by VARCHAR(255),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_by VARCHAR(255),
    actif BOOLEAN NOT NULL DEFAULT TRUE,
    activate_at TIMESTAMP WITHOUT TIME ZONE,
    activate_by VARCHAR(255),
    nom VARCHAR(64) NOT NULL,
    description VARCHAR(256),
    CONSTRAINT pk_role PRIMARY KEY (role_id)
);

