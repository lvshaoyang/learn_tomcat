package ex01.pyrmont;

import java.io.IOException;
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

    /**
     * 怎么从字节输入流中获取uri？
     */
    public void parse(){
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];

        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        for(int j = 0; j < i; j++){
            System.out.println("this is buffer[j] --" + buffer[j]);
            request.append((char)buffer[j]);
        }

        uri = parseUri(request.toString());
    }

    private String parseUri(String requestString){
        int index1,index2;
        index1 = requestString.indexOf(' ');
        if(index1 != -1){
            index2 = requestString.indexOf(' ',index1+1);
            if(index2 > index1){
                return requestString.substring(index1 + 1,index2);
            }
        }
        return null;
    }

}
