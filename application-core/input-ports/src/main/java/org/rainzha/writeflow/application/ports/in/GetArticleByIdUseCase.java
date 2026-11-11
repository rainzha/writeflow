package org.rainzha.writeflow.application.ports.in;

import org.rainzha.writeflow.application.query.GetArticleByIdQuery;
import org.rainzha.writeflow.kernel.application.annotation.UseCase;
import org.rainzha.writeflow.kernel.dto.ArticleDTO;
import org.rainzha.writeflow.kernel.error.Error;
import org.rainzha.writeflow.kernel.query.core.QueryHandler;

/**
 * Use case to retrieve an article by its identifier.
 *
 * @author Enrique Medina Montenegro
 * @see UseCase
 */
@UseCase
public interface GetArticleByIdUseCase extends
        QueryHandler<Error, ArticleDTO, GetArticleByIdQuery> {
}