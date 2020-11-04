public class Conversation {
    protected int totalMessages;
    protected MostRecentMessage mostRecentMessage;
    protected String userId;
    protected String firstName;
    protected String lastName;
    protected String avatar;

    public Conversation(int count, User user, Message message) {
        this.totalMessages = count;
        this.userId = user.id;
        this.avatar = user.avatar;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.mostRecentMessage = message.convertMessage();
    }

    public MostRecentMessage getMostRecentMessage() {
        return mostRecentMessage;
    }

    public String toString() {
        return "{" +
                "avatar:"+ this.avatar + ", " +
                "firstName:" + this.firstName + ", " +
                "lastName:" + this.lastName + ", " +
                "mostRecentMessage:" +
                 "{" +

                "content:"+ this.mostRecentMessage.content + ", " +
                "timestamp:" + this.mostRecentMessage.timestamp + ", " +
                "userId:" + this.mostRecentMessage.senderUserId + ", " +
                "}, " +
                "totalMessages:" + totalMessages + ", " +
                "userId:" + userId;
    }
}
