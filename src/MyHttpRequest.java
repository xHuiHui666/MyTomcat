import java.io.IOException;
import java.io.InputStream;

public class MyHttpRequest { // 请求

    private InputStream inputStream;// 输入流
    private String uri; // 目标资源

    public MyHttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    // 解析输入流, 从输入流中取出数据
    public void parse(){
        try {
            // 定义字节数组, 从输入流读取后的内容都存放到该数组中
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            // 将该字节数组转成字符串类型
            String request = new String(bytes);
            // 解析uri路径
             parseUri(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将用户访问的路径截取出来 例:/index.html
    private void parseUri(String request){
        int index1,index2;
        // index1为第一个空格索引位置
        index1 = request.indexOf(' ');
        // index2为第二个空格索引位置
        index2 = request.indexOf(' ',index1 + 1);
       uri  = request.substring(index1 + 1,index2);
    }

    // 返回这个路径
    public String getUri(){
        return this.uri;
    }

}
