package com.donii.GameCenter.casino.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String name) {
        super("Пользователь с именем " + name + " не был найден");
    }
}
