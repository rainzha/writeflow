package org.rainzha.writeflow.application.command;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.rainzha.writeflow.kernel.error.Error;

import java.time.Instant;

import static org.assertj.core.api.BDDAssertions.then;

class PublishArticleCommandTest {

    private static final String VALID_ARTICLE_ID = "11111111-1111-1111-1111-111111111111";
    private static final String VALID_AUTHOR_ID = "22222222-2222-2222-2222-222222222222";

    @Test
    void shouldCreateValidCommand_whenAllValidFieldsProvided() {
        // given
        final Instant publicationDate = Instant.parse("2026-06-07T10:15:30.00Z");

        // when
        final Either<Error, PublishArticleCommand> result =
                PublishArticleCommand.validateThenCreate(VALID_ARTICLE_ID, VALID_AUTHOR_ID, publicationDate);

        // then
        then(result.isRight()).isTrue();
        final PublishArticleCommand command = result.get();
        then(command.articleId()).isEqualTo(VALID_ARTICLE_ID);
        then(command.authorId()).isEqualTo(VALID_AUTHOR_ID);
        then(command.publicationDate()).isEqualTo(publicationDate);
    }

}
