package org.rainzha.writeflow.kernel.command.core;

import io.vavr.control.Either;
import org.rainzha.writeflow.kernel.command.Command;

/**
 * A handler for a {@link Command}. Notice that it does not return any value.
 *
 * @param <E> type of error
 * @param <C> type of command
 * @author Enrique Medina Montenegro
 * @see <a href="https://martinfowler.com/bliki/CommandQuerySeparation.html">CQS – Command-Query Separation</a>
 */
public interface CommandHandler<E, C extends Command> {
    Either<E, Void> handle(final C command);
}