package com.tim07.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tim07.domain.DTO.FriendDTO;
import com.tim07.domain.Entity.Friendship;
import com.tim07.domain.Entity.RegisteredUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivana Zeljkovic on 17-Apr-17.
 */
public class CustomFriendshipSerializer extends StdSerializer<Map<RegisteredUser, Friendship>>{
    public CustomFriendshipSerializer() {
        this(null);
    }

    public CustomFriendshipSerializer(Class<Map<RegisteredUser, Friendship>> t) {
        super(t);
    }

    @Override
    public void serialize(
            Map<RegisteredUser, Friendship> friendships,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException {

        List<FriendDTO> ids = new ArrayList<FriendDTO>();
        for (Friendship friendship : friendships.values()) {
            ids.add(new FriendDTO(friendship.getSecondUser().getName(), friendship.getSecondUser().getLastname(),
                    friendship.getSecondUser().getUsername(), friendship.getStatus()));
        }
        generator.writeObject(ids);
    }
}
