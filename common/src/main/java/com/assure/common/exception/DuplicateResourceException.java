package com.assure.common.exception;

public class DuplicateResourceException extends BaseException {
    public DuplicateResourceException(String resource, String field, String value) {
        super(String.format("%s with %s '%s' already exists", resource, field, value),
              "DUPLICATE_RESOURCE", 409);
    }
}
