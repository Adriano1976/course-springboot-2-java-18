package com.workshop.course.services.exceptions;

/**
 * Classe responsável em controlar os dados de entreda da exceção da superclasse.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }
}
