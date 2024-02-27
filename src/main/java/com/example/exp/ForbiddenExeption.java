package com.example.exp;

public class ForbiddenExeption extends RuntimeException{
        public ForbiddenExeption(String massage){
            super(massage);
        }
}
