package com.workshop.course.services.exceptions;

/**
 * Classe responsável em manter uma relação de exceção entre a aplicação e a classe nativa {@link RuntimeException}.
 */
public class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }
}
