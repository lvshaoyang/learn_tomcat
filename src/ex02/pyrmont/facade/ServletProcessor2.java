package ex02.pyrmont.facade;

import ex02.pyrmont.Constants;
import ex02.pyrmont.Request;
import ex02.pyrmont.Response;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author lvsy
 * @date 2018/11/24 10:07
 */
public class ServletProcessor2 {
    public void process(Request request, Response response){
        //1、根据request中请求内容，获取servlet的名字
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader loader = null;
        //2、使用URLClassLoader加载项目
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classpath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file",null,classpath.getCanonicalPath()) + File.separator).toString();
            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        //3、根据全路径类名，使用反射获取请求的servlet的实例
        Class myClass = null;
        try {
            myClass = loader.loadClass("ex02.pyrmont." + servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        //4、获取实例后，使用实例调用其service()方法
        Servlet servlet = null;
        try {
            servlet = (Servlet) myClass.newInstance();
            RequestFacade requestFacade = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);
            servlet.service((ServletRequest) requestFacade,(ServletResponse) responseFacade);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
