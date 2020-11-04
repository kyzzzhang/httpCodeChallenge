import java.sql.Timestamp;

public class Message {
    public String content;
    public String timestamp;
    public String fromUserId;
    public String toUserId;

    public Message ( String fromUserId, String toUserId, String timestamp, String content) {
        this.content = content;
        this.timestamp = timestamp;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    public String toString() {
        return content + " " + timestamp + " " + fromUserId + " " + toUserId;
    }

    public MostRecentMessage convertMessage() {
        return new MostRecentMessage(content, timestamp, fromUserId);
    }
}
