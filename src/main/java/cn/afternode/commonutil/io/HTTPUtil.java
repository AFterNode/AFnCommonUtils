package cn.afternode.commonutil.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class HTTPUtil {
    public static String get(String httpUrl, int timeout) throws IOException{
        HttpURLConnection connection;
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();

        URL url = new URL(httpUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(timeout);
        connection.connect();
        if (connection.getResponseCode() == 200) {
            is = connection.getInputStream();
            if (null != is) {
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String temp;
                while (null != (temp = br.readLine())) {
                    result.append(temp);
                }
            }
        }

        if (br != null) {
            br.close();
        }
        if (is != null) {
            is.close();
        }

        connection.disconnect();
        return result.toString();
    }

    public static String post(String httpUrl,
                                String param,
                                int connectTimeout, int readTimeout)
    throws IOException {
        StringBuilder result = new StringBuilder();

        HttpURLConnection connection;
        OutputStream os = null;
        InputStream is = null;
        BufferedReader br = null;

        URL url = new URL(httpUrl);

        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setConnectTimeout(connectTimeout);

        connection.setReadTimeout(readTimeout);

        connection.setDoOutput(true);
        connection.setDoInput(true);

        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");

        if (null != param && param.equals("")) {
            os = connection.getOutputStream();
            os.write(param.getBytes(StandardCharsets.UTF_8));
        }

        if (connection.getResponseCode() == 200) {
            is = connection.getInputStream();
            if (null != is) {
                br = new BufferedReader(new InputStreamReader(is, "GBK"));
                String temp;
                while (null != (temp = br.readLine())) {
                    result.append(temp);
                    result.append("\r\n");
                }
            }
        }

        if(br!=null){
            br.close();
        }
        if(os!=null){
            os.close();
        }
        if(is!=null){
            is.close();
        }

        connection.disconnect();
        return result.toString();
    }

    public static String post(String url, String parameter, int timeout)
            throws IOException {
        return post(url, parameter, timeout, timeout);
    }

    public static String post(String url, String parameter) throws IOException {
        return post(url, parameter, 15000, 15000);
    }

    public static String get(String url) throws IOException {
        return get(url, 15000);
    }

    public static String get(String url, HashMap<String, Object> parameter, int timeout)
        throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (String key: parameter.keySet()) {
            sb.append(key)
                    .append("=")
                    .append(parameter.get(key).toString());
        }
        return get(url + parameter, timeout);
    }

    public static String get(String url, HashMap<String, Object> parameter)
    throws IOException {
        return get(url, parameter, 15000);
    }
}
