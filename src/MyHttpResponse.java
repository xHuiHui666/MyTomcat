import java.io.*;

public class MyHttpResponse {// 响应

    private OutputStream outputStream;

    public MyHttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendRedirect(String uri){

        // 判断uri是否存在
        // 不存在返回404
        // 存在直接返回目标资源数据

      String fileName =  System.getProperty("user.dir") + "/WebContent" + uri ;
        File file = new File(fileName);
        if (file.exists()){
            try {
                // 返回目标资源
                FileInputStream inputStream = new FileInputStream(file);
                // 设置数组大小为文件的长度
                byte[] bytes = new byte[(int)file.length()];
                // 将该文件的内容读取到bytes数组中
                inputStream.read(bytes);
                // 将该数组转成String类型, 方便后面作响应
                String result = new String(bytes);
             String response =   getResponseMessage("200",result);
               this.outputStream.write(response.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                // 返回404
              String error = getResponseMessage("404","404 File Not Found!");
                this.outputStream.write(error.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getResponseMessage(String code,String message){
        return "HTTP/1.1 " + code + "\r\n"
                +"Content-type: text/html\r\n"
                +"Content-Length: " + message.length()
                +"\r\n"
                +"\r\n"
                +message;
    }

}
