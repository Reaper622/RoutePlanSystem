package com.may.routeplansystem.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetWorkUtil {

    public static String visitUrl(String urlStr) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10 * 1000);
            connection.setReadTimeout(10 * 1000);

            connection.connect();

            if (connection.getResponseCode() == 200) {
                if (connection.getContentLength() > 0) {
                    BufferedInputStream bufferedInputStream =
                            new BufferedInputStream(connection.getInputStream());
                    int len;
                    byte[] bytes = new byte[1024];

                    while ((len = bufferedInputStream.read(bytes)) > 0) {
                        String str = new String(bytes, 0, len);
                        result.append(str);
                    }
                }
            }
        } catch (IOException e) {
            throw new URLVisitException("访问URl出现了错误");
        }

        return result.toString();
    }

    public static class URLVisitException extends RuntimeException {

        public URLVisitException(String msg) {
            super(msg);
        }

    }
}
