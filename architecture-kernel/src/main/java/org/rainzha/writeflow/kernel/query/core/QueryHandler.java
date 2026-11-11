package org.rainzha.writeflow.kernel.query.core;

import io.vavr.control.Either;
import org.rainzha.writeflow.kernel.query.Query;


public interface QueryHandler<E, R, Q extends Query> {
    Either<E, R> handle(final Q query);
}