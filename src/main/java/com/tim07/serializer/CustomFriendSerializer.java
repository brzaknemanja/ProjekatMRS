package com.tim07.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tim07.domain.Entity.RegisteredUser;

import java.io.IOException;

/**
 * Created by Ivana Zeljkovic on 17-Apr-17.
 */
public class CustomFriendSerializer extends StdSerializer<RegisteredUser> {
    public CustomFriendSerializer() {
        this(null);
    }

    public CustomFriendSerializer(Class<RegisteredUser> t) {
        super(t);
    }

    @Override
    public void serialize(
            RegisteredUser registeredUser,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException {

        generator.writeObject(registeredUser.getUsername());
    }
}
