import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHttpServer {

    // 端口
    private int port = 8080;

    // 接收请求的方法
    public void receiving(){
        // 创建socket服务
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true){
                // 获取连接对象
                Socket socket = serverSocket.accept();
                // 获取连接对象的输入流
                InputStream inputStream = socket.getInputStream();
                // 创建request对象
                MyHttpRequest request = new MyHttpRequest(inputStream);
                // 解析请求
                 request.parse();
                // 创建response对象
                OutputStream outputStream = socket.getOutputStream();
                MyHttpResponse response = new MyHttpResponse(outputStream);
                // 进行响应
                response.sendRedirect(request.getUri());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
