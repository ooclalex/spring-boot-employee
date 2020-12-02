package com.thoughtworks.springbootemployee.exception;

public class DuplicatedIdException extends Exception{
    public DuplicatedIdException(){
        super("Duplicated ID");
    }
}
