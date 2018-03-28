package group.wenjian.takeout;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sumdoo on 2018/3/26.
 */

public class Utils {

    public static String getStringFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        os.close();
        return state;
    }

    public static String  post(String uri,String param){
        HttpURLConnection conn = null;
        String response = null;
        try{
            String[] aa = param.split("&");
            JSONObject obj = new JSONObject();
            for(int i = 0; i<aa.length; i++){
                obj.put(aa[i].split("=")[0],aa[i].split("=")[1]);
            }
            URL url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 必须设置服务器读取文件的格式为json
            conn.setRequestProperty("contentType", "application/json");
            conn.setUseCaches(false);
            conn.setReadTimeout(5000);// 设置读取超时为5秒
            conn.setConnectTimeout(10000);// 设置连接网络超时为10秒
            conn.setDoOutput(true);// 设置此方法,允许向服务器输出内容
            OutputStream out = conn.getOutputStream();
            out.write((obj.toString()).getBytes());
            out.flush();
            out.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                response = getStringFromInputStream(is);
            } else {
                throw new NetworkErrorException("response status is "+responseCode);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (conn != null) {
                conn.disconnect();// 关闭连接
            }
        }
        return response;
    }

    public static String get(String uri){
        String response = null;
        try{
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            if(conn.getResponseCode() == 200){
                System.out.println("请求数据成功");
                InputStream is = conn.getInputStream();
                response = getStringFromInputStream(is);
            }else{
                System.out.println("请求数据失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("请求数据失败");
        }
        return response;
    }

    public static void writeFile(String msg, String filename, AppCompatActivity activity,Callback callback){
        try {

            FileOutputStream fos = activity.openFileOutput(filename, MODE_PRIVATE);//获得FileOutputStream

            //将要写入的字符串转换为byte数组

            byte[]  bytes = msg.getBytes();

            fos.write(bytes);//将byte数组写入文件

            fos.close();//关闭文件输出流
            callback.success();

        } catch (Exception e) {
            e.printStackTrace();
            callback.failed();
        }
    }


    public static String readFile(String filename,AppCompatActivity activity, Callback callback){

        String result = "";

        try{

            FileInputStream fis = activity.openFileInput(filename);

            //获取文件长度
            int lenght = fis.available();

            byte[] buffer = new byte[lenght];

            fis.read(buffer);

            //将byte数组转换成指定格式的字符串
            result = new String(buffer, "UTF-8");
            callback.success();

        } catch (Exception e) {
            e.printStackTrace();
            callback.failed();

        }

        return  result;
    }

    public interface Callback{
        void success();
        void failed();
    }

    public static  String getTime(){
        String time = null;
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect(); // 发出连接
            long ld = uc.getDate(); // 取得网站日期时间
            Date date = new Date(ld); // 转换为标准时间对象
            // 分别取得时间中的小时，分钟和秒，并输出
            System.out.println(
                    date + ", " + date.getHours() + "时" + date.getMinutes()
                            + "分" + date.getSeconds() + "秒");
            time = date.getHours() + ":" + date.getMinutes();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return time;
    }


}
