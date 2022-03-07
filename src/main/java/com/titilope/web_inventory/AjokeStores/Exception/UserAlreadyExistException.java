package com.titilope.web_inventory.AjokeStores.Exception;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException() {
        super();
    }


    public UserAlreadyExistException(String message) {
        super(message);
    }


    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
