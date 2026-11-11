package org.rainzha.writeflow.application.command;

import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.rainzha.writeflow.kernel.command.Command;
import org.rainzha.writeflow.kernel.error.Error;
import org.rainzha.writeflow.kernel.validation.ValidationError;
import org.rainzha.writeflow.kernel.validation.Validations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Command to encapsulate a request to record an integration event for a published article.
 *
 * <p>WriteFlow is Either-only: commands carry primitives only. The raw identifiers are validated as
 * syntactically valid UUIDs and the publication instant as mandatory at the boundary, but the validated
 * values are stored and exposed as raw {@link String}/{@link Instant} so that no domain type leaks
 * into the input-ports layer; the handler re-parses them when rebuilding the integration event.</p>
 *
 * @author Enrique Medina Montenegro
 * @see Command
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RecordIntegrationEventCommand implements Command {

    private final String articleId;
    private final String authorId;
    private final Instant publishedAt;

    /**
     * Validates the raw request data and creates a {@link RecordIntegrationEventCommand} if everything is valid.
     *
     * @param articleId   the raw article identifier
     * @param authorId    the raw author identifier
     * @param publishedAt the instant at which the article was published
     * @return {@link Either#right} with the command when valid, otherwise {@link Either#left} with the accumulated errors
     */
    public static Either<Error, RecordIntegrationEventCommand> validateThenCreate(
            final String articleId,
            final String authorId,
            final Instant publishedAt
    ) {
        final List<ValidationError> errors = new ArrayList<>();

        Validations.validUuid(articleId, "articleId").peekLeft(errors::add);
        Validations.validUuid(authorId, "authorId").peekLeft(errors::add);
        Validations.mandatory(publishedAt, "publishedAt").peekLeft(errors::add);

        if (!errors.isEmpty()) {
            return Either.left(new Error.ValidationErrors(List.copyOf(errors)));
        }

        return Either.right(new RecordIntegrationEventCommand(articleId, authorId, publishedAt));
    }
}