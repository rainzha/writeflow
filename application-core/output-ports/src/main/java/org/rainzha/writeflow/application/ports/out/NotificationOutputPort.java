package org.rainzha.writeflow.application.ports.out;

import io.vavr.control.Either;
import org.rainzha.writeflow.kernel.application.annotation.OutputPort;
import org.rainzha.writeflow.kernel.dto.NotificationTextDTO;
import org.rainzha.writeflow.kernel.dto.NotificationTypeDTO;
import org.rainzha.writeflow.kernel.error.Error;

/**
 * This port abstracts away the delivery of notifications to the subscribers of an author,
 * decoupling the application core from the concrete notification channel (e.g. email, push)
 * provided by the infrastructure.
 *
 * @author Enrique Medina Montenegro
 */
@OutputPort
public interface NotificationOutputPort {

    /**
     * Notifies the relevant subscribers with the given text and notification type.
     *
     * @param text the notification text to deliver
     * @param type the type of notification being delivered
     * @return {@link Either} the resulting {@link Error} on the left, or {@code Void} on success
     */
    Either<Error, Void> notify(final NotificationTextDTO text, final NotificationTypeDTO type);

}