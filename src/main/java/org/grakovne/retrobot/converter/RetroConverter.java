package org.grakovne.retrobot.converter;

import java.util.function.Supplier;

/**
 * Know how to convert ORM entity into UI-friendly way.
 */
public abstract class RetroConverter<Entity, Response> {

    private final Supplier<Response> inflate;

    protected RetroConverter(Supplier<Response> inflate) {
        this.inflate = inflate;
    }

    /**
     * Know how to convert ORM entity into UI-friendly way.
     */
    public Response convert(Entity entity) {
        return from(entity, inflate.get());
    }

    /**
     * Know how to map entity fields to blank created response.
     */
    protected abstract Response from(Entity entity, Response response);
}
