package ex01.pyrmont;

import java.io.InputStream;

/**
 *
 * @date 2018/10/16 23:53
 */
public class Request {
    private InputStream inputStream;
    private String uri;

    public Request(){

    }

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUri(){
        return uri;
    }

    public void parse(){
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
    }

}
