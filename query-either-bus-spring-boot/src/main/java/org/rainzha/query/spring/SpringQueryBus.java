package org.rainzha.query.spring;

import io.vavr.control.Either;
import org.rainzha.writeflow.kernel.query.Query;
import org.rainzha.writeflow.kernel.query.core.QueryBus;
import org.rainzha.writeflow.kernel.query.core.QueryHandler;
import org.springframework.context.annotation.Bean;

/**
 * Implementation of a query bus backed by Spring's registry.
 *
 * @author Enrique Medina Montenegro
 */
public class SpringQueryBus implements QueryBus {

    private final Registry registry;

    /**
     * Creates a new instance with the given registry using constructor-based dependency injection.
     *
     * @param registry a wrapper around Spring's application context
     */
    public SpringQueryBus(final Registry registry) {
        this.registry = registry;
    }

    /**
     * Delegates the handling of the query to the corresponding {@link Bean} from Spring.
     *
     * @param query the query object
     * @param <E>   the type of the error
     * @param <R>   the type of the result
     * @param <Q>   the type of the query
     * @return either success with result, or an error if anything goes wrong
     */
    @Override
    @SuppressWarnings("unchecked")
    public <E, R, Q extends Query> Either<E, R> query(final Q query) {
        Class<Q> queryClass = (Class<Q>) query.getClass();
        QueryHandler<E, R, Q> queryHandler = this.registry.get(queryClass);
        return queryHandler.handle(query);
    }

}