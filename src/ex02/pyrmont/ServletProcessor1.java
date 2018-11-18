package ex02.pyrmont;

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
 * @date 2018/11/7 23:13
 * URL类，还有反射这些还要再熟悉
 */
public class ServletProcessor1 {
    public void process(Request request,Response response){
        //此处request中截取的uri是以这样的形式存在的：/servlet/servletNmae
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;

            File classpath = new File(Constants.WEB_ROOT );
            //使用下面的方法,可以组成一个包含资源库(servlet类可以被找到的地方)的字符串,做为URL构造方法的第二个参数.
            String repository = (new URL("file",null,classpath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null,repository,streamHandler);
            /**
             * 此loader加载了整个项目下的所有文件
             * 对于这个servlet，此加载器会在指向的目录里边查找。
             * 注意:在servlet容器里,  一个类加载器可以找到servlet的地方被称为资源库.
             */

            loader = new URLClassLoader(urls);
        }catch (IOException e){
            System.out.println(e.toString());
        }

        Class myClass = null;

        try {
//            myClass = loader.loadClass(servletName);此处如果不使用全类名的话,会报ClassNotFoundException异常,所以修改为下面的全类名路径
            myClass = loader.loadClass("ex02.pyrmont."+servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;

        try {
            System.out.println(myClass.toString());
            servlet = (Servlet)myClass.newInstance();
            servlet.service((ServletRequest) request,(ServletResponse) response);
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
