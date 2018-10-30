package ex02.pyrmont;

import java.io.IOException;

/**
 * @date 2018/10/30 23:15
 */
public class StaticResourceProcessor {
    public void process(Request request,Response response){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
