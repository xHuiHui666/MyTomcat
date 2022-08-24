public class Start {

    public static void main(String[] args) {
        System.out.println("Server startUp");
        MyHttpServer myHttpServer = new MyHttpServer();
        myHttpServer.receiving();
    }
}
