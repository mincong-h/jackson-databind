package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

/**
 * Simple general purpose serializer, useful for any
 * type for which {@link Object#toString} returns the desired JSONvalue.
 *<p>
 * NOTE: this is NOT meant to be used as a base class for custom serializers;
 * instead, consider base type {@link ToStringSerializerBase} if you need similar
 * functionality.
 */
@JacksonStdImpl
public final class ToStringSerializer
    extends ToStringSerializerBase
{
    // Singleton instance to use.
    public final static ToStringSerializer instance = new ToStringSerializer(Object.class);

    private final Value _jsonFormat; // nullable

    // Only needed to support legacy use via annotations
    protected ToStringSerializer() { this(Object.class); }

    public ToStringSerializer(Class<?> handledType) {
        this(handledType, null);
    }

    ToStringSerializer(Class<?> handledType, Value format) {
        super(handledType);
        this._jsonFormat = format;
    }

    @Override
    public String valueToString(Object value) {
        if (_jsonFormat != null && _jsonFormat.hasPattern()) {
            return String.format(_jsonFormat.getPattern(), value);
        }
        return value.toString();
    }
}
