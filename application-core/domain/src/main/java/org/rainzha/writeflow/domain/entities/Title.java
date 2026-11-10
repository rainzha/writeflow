package org.rainzha.writeflow.domain.entities;

import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.rainzha.writeflow.kernel.error.Error;
import org.rainzha.writeflow.kernel.validation.ValidationError;
import org.rainzha.writeflow.kernel.validation.Validations;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Title {

    private static final int MAX_LENGTH = 500;

    @EqualsAndHashCode.Include
    private final String value;

    public static Either<Error, Title> validateThenCreate(final String title) {
        final List<ValidationError> errors = new ArrayList<>();
        Validations.mandatoryText(title, "title").peekLeft(errors::add);
        Validations.maxLength(title, MAX_LENGTH, "title").peekLeft(errors::add);

        return errors.isEmpty()
                ? Either.right(new Title(title))
                : Either.left(new Error.ValidationErrors(List.copyOf(errors)));
    }
}
