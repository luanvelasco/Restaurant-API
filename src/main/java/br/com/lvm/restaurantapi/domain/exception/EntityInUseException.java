package br.com.lvm.restaurantapi.domain.exception;

public class EntityInUseException extends RuntimeException{
    public EntityInUseException(String message) {
        super(message);
    }
}
