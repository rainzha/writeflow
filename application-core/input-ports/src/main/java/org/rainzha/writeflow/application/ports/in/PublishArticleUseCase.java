package org.rainzha.writeflow.application.ports.in;

import org.rainzha.writeflow.application.command.PublishArticleCommand;
import org.rainzha.writeflow.kernel.application.annotation.UseCase;
import org.rainzha.writeflow.kernel.command.core.CommandHandler;
import org.rainzha.writeflow.kernel.error.Error;

/**
 * Use case to publish an existing draft article.
 *
 * @author Enrique Medina Montenegro
 * @see UseCase
 */
@UseCase
public interface PublishArticleUseCase extends
        CommandHandler<Error, PublishArticleCommand> {
}
