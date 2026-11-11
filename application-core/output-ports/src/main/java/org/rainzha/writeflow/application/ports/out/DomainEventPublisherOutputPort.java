package org.rainzha.writeflow.application.ports.out;

import io.vavr.control.Either;
import org.rainzha.writeflow.kernel.application.annotation.OutputPort;
import org.rainzha.writeflow.kernel.error.Error;
import org.rainzha.writeflow.kernel.event.DomainEvent;

import java.util.List;

/**
 * This port abstracts away the publication of domain events raised by aggregates within the
 * application's own boundary, decoupling the application core from the concrete event-dispatch
 * mechanism provided by the infrastructure (e.g. Spring's {@code ApplicationEventPublisher}).
 *
 * @author Enrique Medina Montenegro
 */
@OutputPort
public interface DomainEventPublisherOutputPort {

    /**
     * Publishes the given list of domain events so that interested listeners can react to them.
     *
     * @param events the domain events to publish
     * @return either success, or an error if the event could not be published
     */
    Either<Error, Void> publish(final List<DomainEvent> events);

}