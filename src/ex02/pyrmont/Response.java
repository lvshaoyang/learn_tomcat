package ex02.pyrmont;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @author lvsy
 * @date 2018/10/28 22:37
 */
public class Response implements ServletResponse {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream outputStream;
    PrintWriter writer;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fileInputStream = null;

        try {
            File file = new File(Constants.WEB_ROOT + request.getUri());
            fileInputStream = new FileInputStream(file);

            int ch = fileInputStream.read(bytes,0,BUFFER_SIZE);
            while (ch != -1){
                outputStream.write(bytes,0,BUFFER_SIZE);
                ch = fileInputStream.read(bytes,0,BUFFER_SIZE);
            }

        } catch (FileNotFoundException e) {
            String erroMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type:text/html\r\n" +
                    "Content-length:23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            outputStream.write(erroMessage.getBytes());
        } finally {
            if(fileInputStream != null){
                fileInputStream.close();
            }
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(outputStream,true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
