-- V1__initial_schema.sql
CREATE TABLE permission (
    permission_id UUID NOT NULL,
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
    code VARCHAR(64) NOT NULL,
    description VARCHAR(256),
    parent_id UUID,
    CONSTRAINT pk_permission PRIMARY KEY (permission_id),
    CONSTRAINT fk_permission_parent FOREIGN KEY (parent_id) REFERENCES permission(permission_id)
);

