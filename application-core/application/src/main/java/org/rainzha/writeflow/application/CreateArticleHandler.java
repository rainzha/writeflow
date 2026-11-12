package org.rainzha.writeflow.application;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.rainzha.writeflow.application.command.CreateArticleCommand;
import org.rainzha.writeflow.application.ports.in.CreateArticleUseCase;
import org.rainzha.writeflow.domain.entities.*;
import org.rainzha.writeflow.domain.repositories.ArticleRepository;
import org.rainzha.writeflow.kernel.application.annotation.ApplicationService;
import org.rainzha.writeflow.kernel.error.Error;
import org.rainzha.writeflow.kernel.transactional.Transactional;

/**
 * Orchestration logic for the use case to create a new draft article.
 *
 * <p>The command carries primitives only, so the handler re-parses the raw identifiers and
 * text fields into their typed value objects, assembles the {@link Article} aggregate in
 * {@link org.rainzha.writeflow.domain.entities.ArticleStatus#DRAFT} and persists it. Every
 * step is chained over {@link Either} so that any failure short-circuits the remainder of the
 * pipeline. No domain event is raised on creation; only publication raises
 * {@link org.rainzha.writeflow.kernel.event.ArticlePublished}.</p>
 *
 * @author Enrique Medina Montenegro
 * @see ApplicationService
 */
@ApplicationService
@RequiredArgsConstructor
class CreateArticleHandler implements CreateArticleUseCase {

    private final ArticleRepository articleRepository;

    /**
     * Handles the command.
     *
     * @param command command to handle
     * @return {@link Either#right} with {@code null} on success, otherwise {@link Either#left} with the error
     */
    @Override
    @Transactional(rollbackForClassNameWithEither = {"org.rainzha.writeflow.kernel.error.Error"})
    public Either<Error, Void> handle(final CreateArticleCommand command) {
        return ArticleId.validateThenCreate(command.articleId())
                .flatMap(articleId -> AuthorId.validateThenCreate(command.authorId())
                        .flatMap(authorId -> Title.validateThenCreate(command.title())
                                .flatMap(title -> Content.validateThenCreate(command.content())
                                        .flatMap(content -> Article.validateThenCreate(articleId, title, content, authorId)))))
                .flatMap(this.articleRepository::save);
    }

}