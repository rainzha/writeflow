package org.rainzha.writeflow.application;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.rainzha.writeflow.application.command.NotifySubscribersCommand;
import org.rainzha.writeflow.application.ports.in.NotifySubscribersUseCase;
import org.rainzha.writeflow.application.ports.out.NotificationOutputPort;
import org.rainzha.writeflow.kernel.application.annotation.ApplicationService;
import org.rainzha.writeflow.kernel.dto.NotificationTextDTO;
import org.rainzha.writeflow.kernel.dto.NotificationTypeDTO;
import org.rainzha.writeflow.kernel.error.Error;
import org.rainzha.writeflow.kernel.transactional.Transactional;

/**
 * Orchestration logic for the use case to notify the subscribers of an author about a
 * published article.
 *
 * <p>The handler builds the notification payload and delegates delivery to the
 * {@link NotificationOutputPort}, keeping the application core agnostic of the concrete
 * notification channel.</p>
 *
 * @author Enrique Medina Montenegro
 * @see ApplicationService
 */
@ApplicationService
@RequiredArgsConstructor
class NotifySubscribersHandler implements NotifySubscribersUseCase {

    private final NotificationOutputPort notificationOutputPort;

    /**
     * Handles the command.
     *
     * @param command command to handle
     * @return {@link Either#right} with {@code null} on success, otherwise {@link Either#left} with the error
     */
    @Override
    @Transactional(rollbackForClassNameWithEither = {"org.rainzha.writeflow.kernel.error.Error"})
    public Either<Error, Void> handle(final NotifySubscribersCommand command) {
        return this.notificationOutputPort.notify(
                new NotificationTextDTO("An article you follow has just been published"),
                NotificationTypeDTO.ARTICLE_PUBLISHED);
    }

}