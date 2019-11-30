package me.morpheus.metropolis.configurate.serialize;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;

import javax.annotation.Nullable;
import java.time.Duration;
import java.time.format.DateTimeParseException;

public class DurationSerializer implements TypeSerializer<Duration> {

    @Nullable
    @Override
    public Duration deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
        try {
            return Duration.parse(value.getString());
        } catch (DateTimeParseException ex) {
            throw new ObjectMappingException("Value not a Duration", ex);
        }
    }

    @Override
    public void serialize(TypeToken<?> type, @Nullable Duration obj, ConfigurationNode value) throws ObjectMappingException {
        value.setValue(obj.toString());
    }

}

