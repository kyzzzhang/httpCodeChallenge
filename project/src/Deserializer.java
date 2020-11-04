import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.*;

public class Deserializer extends StdDeserializer<Owner> {
    private Map<String, Message> messageMap = new HashMap<>();
    private Map<String, Integer> conversationCountMap = new HashMap<>();
    private Map<String, User> userMap = new HashMap<>();

    public Deserializer() {
        this(null);
    }

    public Deserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Owner deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String userId = node.get("userId").asText();
        processMessage(node, userId);
        processUsers(node);
        return new Owner(userId, messageMap, userMap, conversationCountMap);
    }

    private void processUsers (JsonNode node) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Iterator iterator = node.get("users").elements();
        while (iterator.hasNext()) {
            User user = mapper.readValue(iterator.next().toString(), User.class);
            userMap.put(user.id, user);
        }
    }

    private void processMessage(JsonNode node, String userId) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Message.class, new MessageDeserializer());
        mapper.registerModule(module);
        Iterator iterator = node.get("messages").elements();
        while (iterator.hasNext()) {
            Message message = mapper.readValue(iterator.next().toString(), Message.class);
            if (message.fromUserId.equals(userId)) {
                setMessageMap(message.toUserId, message);
                int curCount = conversationCountMap.getOrDefault(message.toUserId, 0);
                conversationCountMap.put(message.toUserId, curCount + 1);
            } else {
                setMessageMap(message.fromUserId, message);
                int curCount = conversationCountMap.getOrDefault(message.fromUserId, 0);
                conversationCountMap.put(message.fromUserId, curCount + 1);
            }
        }
    }

    private void setMessageMap(String id, Message message) {
        if (messageMap.containsKey(id)) {
            Message existMess = messageMap.get(id);
            if (existMess.timestamp.compareTo(message.timestamp) < 0) {
                messageMap.put(id, message);
            }
        } else {
            messageMap.put(id, message);
        }
    }
}
