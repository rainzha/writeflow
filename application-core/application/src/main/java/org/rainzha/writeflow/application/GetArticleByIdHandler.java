package org.rainzha.writeflow.application;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.rainzha.writeflow.application.ports.in.GetArticleByIdUseCase;
import org.rainzha.writeflow.application.query.GetArticleByIdQuery;
import org.rainzha.writeflow.domain.entities.ArticleId;
import org.rainzha.writeflow.domain.repositories.ArticleRepository;
import org.rainzha.writeflow.kernel.application.annotation.ApplicationService;
import org.rainzha.writeflow.kernel.dto.ArticleDTO;
import org.rainzha.writeflow.kernel.error.Error;
import org.rainzha.writeflow.kernel.transactional.Transactional;

/**
 * Orchestration logic for the use case to retrieve an article by its identifier.
 *
 * <p>The raw identifier carried by the query is re-validated into the typed
 * {@link ArticleId} value object before delegating to the repository, so that the read
 * path remains Either-only end to end.</p>
 *
 * @author Enrique Medina Montenegro
 * @see ApplicationService
 */
@ApplicationService
@RequiredArgsConstructor
class GetArticleByIdHandler implements GetArticleByIdUseCase {

    private final ArticleRepository articleRepository;

    /**
     * Handles the query.
     *
     * @param query query to handle
     * @return {@link Either#right} with the article DTO, otherwise {@link Either#left} with the error
     */
    @Override
    @Transactional(rollbackForClassNameWithEither = {"org.rainzha.writeflow.kernel.error.Error"}, readOnly = true)
    public Either<Error, ArticleDTO> handle(final GetArticleByIdQuery query) {
        return ArticleId.validateThenCreate(query.articleId())
                .flatMap(this.articleRepository::findById);
    }

}