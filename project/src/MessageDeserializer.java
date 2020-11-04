import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class MessageDeserializer extends StdDeserializer<Message> {

        public MessageDeserializer() {
            this(null);
        }

        public MessageDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Message deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            JsonNode node = jp.getCodec().readTree(jp);
            String content = node.get("content").asText();
            String senderId = node.get("fromUserId").asText();
            String toUserId = node.get("toUserId").asText();
            String time = node.get("timestamp").asText();
            
            return new Message(senderId, toUserId, time, content);
        }
}
