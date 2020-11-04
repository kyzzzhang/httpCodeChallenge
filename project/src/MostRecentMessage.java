import java.sql.Timestamp;
import com.google.gson.Gson;

public class MostRecentMessage {
    protected String content;
    protected String timestamp;
    protected String senderUserId;

    public MostRecentMessage(String content, String time, String senderUserId) {
        this.content = content;
        this.timestamp = time;
        this.senderUserId = senderUserId;
    }

    public String getTimestamp(){
        return this.timestamp;
    }

    public String toJsonString() {
        String res = new Gson().toJson(this);
        System.out.println("most recent message json string " + res);
        return new Gson().toJson(this);
    }
}
