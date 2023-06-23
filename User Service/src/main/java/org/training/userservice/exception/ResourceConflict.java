package org.training.userservice.exception;

public class ResourceConflict extends RuntimeException {

    public ResourceConflict(String message){
        super(message);
    }

    public ResourceConflict() {
        super("Resource Not found on the server");
    }
}
