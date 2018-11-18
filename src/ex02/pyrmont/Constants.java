package ex02.pyrmont;

import java.io.File;
import java.io.IOException;

/**
 * @date 2018/10/28 22:41
 */
public class Constants {
    public static final String WEB_ROOT = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        System.out.println(WEB_ROOT + File.separator);
        File file = new File(WEB_ROOT);
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getAbsolutePath());
    }
}
