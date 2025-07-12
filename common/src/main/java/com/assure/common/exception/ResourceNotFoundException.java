package com.assure.common.exception;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String resource, String identifier) {
        super(String.format("%s with identifier '%s' not found", resource, identifier),
              "RESOURCE_NOT_FOUND", 404);
    }
}
