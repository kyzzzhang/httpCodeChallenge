public class MainProcessor {
    public static void main(String[] args) throws Exception {
        Client client = new Client();

        System.out.println("Testing - Send GET request");
        client.getData();
        client.postData();
    }
}
