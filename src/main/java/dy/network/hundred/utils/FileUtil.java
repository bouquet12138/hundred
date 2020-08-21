package dy.network.hundred.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

    /**
     * 得到文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffixName(String fileName) {
        if (fileName == null || fileName.equals("")) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1)
            return "";
        String suffixName = fileName.substring(index);
        return suffixName;
    }

    /**
     * 得到文件前缀
     * @param fileName
     * @return
     */
    public static String getPrefixName(String fileName) {
        if (fileName == null || fileName.equals("")) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1)
            return "";
        String suffixName = fileName.substring(0, index);
        return suffixName;
    }

    /**
     * 拷贝文件
     * @param inputStream
     * @param file
     */
    public static void copyInputStreamToFile(InputStream inputStream, File file) {
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            byte[] flush = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(flush)) != -1) {
                outputStream.write(flush, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception exception) {
            }
        }
    }
}
