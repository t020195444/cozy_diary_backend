package com.example.demo.exception;

public class ProjectException extends RuntimeException{
    public ProjectException(){
        super();
    }
    public ProjectException(String message, Throwable cause){
        super(message,cause);
    }
    public ProjectException(String message){
        super(message);
    }
    public ProjectException(Throwable cause){
        super(cause);
    }
}
