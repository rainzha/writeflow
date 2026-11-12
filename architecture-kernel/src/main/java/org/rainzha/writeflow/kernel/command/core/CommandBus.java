package org.rainzha.writeflow.kernel.command.core;

import io.vavr.control.Either;
import org.rainzha.writeflow.kernel.error.Error;

public interface CommandBus {
    <C> Either<Error, Void> execute(C command);
}