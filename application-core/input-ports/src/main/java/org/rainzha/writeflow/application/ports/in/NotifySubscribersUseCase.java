package org.rainzha.writeflow.application.ports.in;

import org.rainzha.writeflow.application.command.NotifySubscribersCommand;
import org.rainzha.writeflow.kernel.application.annotation.UseCase;
import org.rainzha.writeflow.kernel.command.core.CommandHandler;
import org.rainzha.writeflow.kernel.error.Error;

/**
 * Use case to notify the subscribers of an author that a new article has been published.
 *
 * @author Enrique Medina Montenegro
 * @see UseCase
 */
@UseCase
public interface NotifySubscribersUseCase extends CommandHandler<Error, NotifySubscribersCommand> {
}