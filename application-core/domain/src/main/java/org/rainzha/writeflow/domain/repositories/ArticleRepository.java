package org.rainzha.writeflow.domain.repositories;

import io.vavr.control.Either;
import org.rainzha.writeflow.domain.entities.Article;
import org.rainzha.writeflow.domain.entities.ArticleId;
import org.rainzha.writeflow.domain.entities.AuthorId;
import org.rainzha.writeflow.kernel.application.annotation.Repository;
import org.rainzha.writeflow.kernel.dto.ArticleDTO;
import org.rainzha.writeflow.kernel.error.Error;

import java.util.List;

/**
 * Repository port for articles, expressed in terms of the Domain Model.
 *
 * <p>This port lives in the domain module and is annotated with the Architecture Kernel
 * {@link Repository} stereotype (not Spring's). It follows a CQRS-flavoured split: command
 * and read-model methods return domain entities, while query methods return DTOs.
 * {@code readById} returns a bare {@link Either} (never {@code Optional}); the persistence
 * adapter maps a missing article to {@link Error.BusinessError.NotFound}.</p>
 *
 * @author Enrique Medina Montenegro
 */
@Repository
public interface ArticleRepository {

    /**
     * Persists the supplied article.
     *
     * @param article the article to persist
     * @return {@link Either#right} with {@code null} on success, otherwise
     * {@link Either#left} with the failure
     */
    Either<Error, Void> save(final Article article);

    /**
     * Reads the article with the supplied identifier as a domain entity.
     *
     * @param id the identifier of the article
     * @return {@link Either#right} with the article, or {@link Either#left} with a
     * {@link Error.BusinessError.NotFound} when no such article exists
     */
    Either<Error, Article> readById(final ArticleId id);

    /**
     * Finds the article with the supplied identifier as a DTO.
     *
     * @param id the identifier of the article
     * @return {@link Either#right} with the article DTO, or {@link Either#left} with a
     * {@link Error.BusinessError.NotFound} when no such article exists
     */
    Either<Error, ArticleDTO> findById(final ArticleId id);

    /**
     * Finds all articles written by the supplied author as DTOs.
     *
     * @param authorId the identifier of the author
     * @return {@link Either#right} with the list of article DTOs (possibly empty)
     */
    Either<Error, List<ArticleDTO>> findByAuthor(final AuthorId authorId);

}