import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class Serializer extends StdSerializer<Owner> {
    public Serializer() {
        this(null);
    }

    public Serializer(Class<Owner> t) {
        super(t);
    }

    @Override
    public void serialize(
            Owner owner, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException,
            JsonGenerationException {
        jsonGenerator.writeStartObject();
        String s = "";
        for (Conversation c : owner.conversationList) {
            s += c.toString() + ", ";
        }
        jsonGenerator.writeStringField("conversations", s);
        jsonGenerator.writeEndObject();
    }
}
