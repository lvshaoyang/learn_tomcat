package ex01.pyrmont;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lvsy
 * @date 2018/10/16 23:45
 */
public class HttpServer {
    /**
     * WEB_ROOT是存放html和其他文件的目录
     */
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "webroot";
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    public void await(){
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                socket = new Socket();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();


            } catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
}
