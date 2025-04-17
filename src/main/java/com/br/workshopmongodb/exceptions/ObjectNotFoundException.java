package com.br.workshopmongodb.exceptions;

public class ObjectNotFoundException extends RuntimeException {

        public ObjectNotFoundException(String message) {
            super(message);
        }
}
