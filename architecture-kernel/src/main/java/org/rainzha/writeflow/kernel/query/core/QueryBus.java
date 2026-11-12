package org.rainzha.writeflow.kernel.query.core;

import io.vavr.control.Either;
import org.rainzha.writeflow.kernel.query.Query;

public interface QueryBus {
    <E, R, Q extends Query> Either<E, R>
    query(final Q query);
}