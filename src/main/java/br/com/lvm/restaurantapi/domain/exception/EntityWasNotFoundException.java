package br.com.lvm.restaurantapi.domain.exception;

public class EntityWasNotFoundException extends RuntimeException{
    public EntityWasNotFoundException(String message) {
        super(message);
    }
}
