package com.donii.GameCenter.casino.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String name) {
        super("Пользлватель с именем " + name + " уже существует");
    }
}
