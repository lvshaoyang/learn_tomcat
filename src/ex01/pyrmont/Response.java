package ex01.pyrmont;

import java.io.*;

/**
 * @date 2018/10/16 23:53
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream outputStream;

    public static final String SUCCESS_MESSAGE =
            "HTTP/1.1 200 OK\r\n"
                    +"\r\n"
            ;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fileInputStream = null;

        try {
            File file = new File(HttpServer.WEB_ROOT,request.getUri());
            if(file.exists()){
                outputStream.write(SUCCESS_MESSAGE.getBytes());
                fileInputStream = new FileInputStream(file);
                int ch = fileInputStream.read(bytes,0,BUFFER_SIZE);
                while (ch != -1){
                    outputStream.write(bytes,0,ch);
                    ch = fileInputStream.read(bytes,0,BUFFER_SIZE);
                }
            }else {
                String erroMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type:text/html\r\n" +
                        "Content-length:23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                outputStream.write(erroMessage.getBytes());
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            if(fileInputStream != null){
                fileInputStream.close();
            }
        }

    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
