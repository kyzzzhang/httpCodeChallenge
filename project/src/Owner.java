import java.util.Map;
import java.util.PriorityQueue;

public class Owner {
    public Map<String, Message> messagesMap;
    public String userId;
    public Map<String, User> usersMap;
    public Map<String, Integer> countMap;
    protected PriorityQueue<Conversation> conversationList;

    public Owner(String userId, Map<String, Message> messagesMap, Map<String, User> usersMap, Map<String, Integer> countMap) {
        this.userId = userId;
        this.messagesMap = messagesMap;
        this.usersMap = usersMap;
        this.countMap = countMap;
        conversationList = new PriorityQueue<Conversation>((a, b) ->
                -(a.getMostRecentMessage().getTimestamp().compareTo(b.getMostRecentMessage().getTimestamp())));
        processConversation();

    }

    private void processConversation() {
        for (String id : messagesMap.keySet()) {
            Conversation c = new Conversation(countMap.get(id), usersMap.get(id), messagesMap.get(id));
            conversationList.add(c);
        }
    }

}


