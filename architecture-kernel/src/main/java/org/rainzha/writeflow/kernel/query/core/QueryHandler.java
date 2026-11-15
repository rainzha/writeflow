package org.rainzha.writeflow.kernel.query.core;

import io.vavr.control.Either;
import org.rainzha.writeflow.kernel.query.Query;


/**
 * A handler for a {@link Query}.
 *
 * @param <E> type of error
 * @param <R> type of return value
 * @param <Q> type of query
 * @author Enrique Medina Montenegro
 * @see <a href="https://martinfowler.com/bliki/CommandQuerySeparation.html">CQS - Command-Query Separation</a>
 */
public interface QueryHandler<E, R, Q extends Query> {

    /**
     * Handles the query.
     *
     * @param query query to handle
     * @return either success with result, or an error if anything goes wrong
     */
    Either<E, R> handle(final Q query);

}