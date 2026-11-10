package org.rainzha.writeflow.kernel.error;

import org.rainzha.writeflow.kernel.validation.ValidationError;

import java.io.Serializable;
import java.util.List;

/**
 * Hierarchy of possible error values for WriteFlow.
 *
 * @author Enrique Medina Montenegro
 */
public sealed interface Error extends Serializable {

    record ValidationErrors(List<ValidationError> errors) implements Error {
    }

    record MultipleErrors(List<Error> errors) implements Error {
    }

    sealed interface BusinessError extends Error {

        record NotFound(String origin, String entity, String id) implements BusinessError {
        }

        record ArticleAlreadyPublished(String articleId) implements BusinessError {
        }

        record AuthorNotAllowedToPublish(String authorId, String articleId) implements BusinessError {
        }

    }

    sealed interface TechnicalError extends Error {

        record PersistenceError(String origin, String operation, String message) implements TechnicalError {
        }

        record SomethingWentWrong(String origin, String message) implements TechnicalError {
        }

    }

}