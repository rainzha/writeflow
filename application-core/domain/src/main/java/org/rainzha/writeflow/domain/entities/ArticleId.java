package org.rainzha.writeflow.domain.entities;

import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.rainzha.writeflow.kernel.error.Error;
import org.rainzha.writeflow.kernel.validation.Validations;

import java.util.List;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleId {

    @EqualsAndHashCode.Include
    private final String value;

    public static Either<Error, ArticleId> validateThenCreate(final String id) {
        return Validations.validUuid(id, "id")
                .map(ArticleId::new)
                .mapLeft(e -> new Error.ValidationErrors(List.of(e)));
    }
}