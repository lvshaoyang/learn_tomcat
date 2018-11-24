package ex02.pyrmont.facade;

import ex02.pyrmont.Request;
import ex02.pyrmont.Response;
import ex02.pyrmont.StaticResourceProcessor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lvsy
 * @date 2018/11/24 10:32
 */
public class HttpServer2 {

    /**
     * WEB_ROOT是存放html和其他文件的目录
     */
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "webroot";
    private static final String SHUT_DOWN = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer2 httpServer = new HttpServer2();
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
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(outputStream);
                response.setRequest(request);

                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor2 servletProcessor2 = new ServletProcessor2();
                    servletProcessor2.process(request,response);
                }else{
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                    staticResourceProcessor.process(request,response);
                }

                socket.close();
                shutdown = request.getUri().equals(SHUT_DOWN);

            } catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
}
