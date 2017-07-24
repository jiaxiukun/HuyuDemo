package baway.com.huyudemo.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类描述：OkHttp3 网络框架
 * Created by 贾秀坤 on 2017/4/23.
 */

public class HttpUtils {
    //成功码
    private static final int SUCCESS_CODE = 1;
    //错误码
    private static final int ERROR_CODE = 2;
    private static HttpUtils mHttpUtils;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private final Gson mGson;
    //回调处理结果的接口
    public interface HttpCallBack<T>{
        void onSuccess(T t);
        void onFailure(IOException e);
    }
    //私有构造
    private HttpUtils() {
        mOkHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        mHandler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case SUCCESS_CODE:
                        ((HandlerData)msg.obj).success();
                        break;
                    case ERROR_CODE:
                        ((HandlerData)msg.obj).error();
                        break;
                }
            }
        };
        mGson = new Gson();
    }
    //单例模式
    public static HttpUtils getInstance() {
        if (mHttpUtils == null) {
            synchronized (HttpUtils.class) {
                if (mHttpUtils == null) {
                    mHttpUtils = new HttpUtils();
                }
            }
        }
        return mHttpUtils;
    }

    /**
     * 同步get
     * 内部返回response的方法
     * @param url
     * @param map
     * @return Response
     * @throws IOException
     */
    private Response _getSyn(String url,Map<String,Object> map) throws IOException {
        String urlPath=makeUrl(url,map);
        Request request = new Request.Builder()
                .url(urlPath)
                .build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 同步get
     * 内部返回实体bean的方法
     * @param url
     * @param map
     * @return Response
     * @throws IOException
     */
    private <T> T _getSynData(String url, Map<String,Object> map, Class<T> classType) throws IOException {
        Response response = _getSyn(url,map);
        String json = response.body().string();
        T t = mGson.fromJson(json, classType);
        return t;
    }

    /**
     * 异步get
     * 内部返回实体bean的方法
     * @param url
     * @param map
     * @param classType
     * @param httpCallBack
     * @param <T>
     */
    private <T> void _getAsyn(String url, Map<String,Object> map, final Class<T> classType,HttpCallBack httpCallBack){
        String urlPath=makeUrl(url,map);
        Request request=new Request.Builder()
                .url(urlPath)
                .build();
        Call call=mOkHttpClient.newCall(request);
        final HandlerData<T> hd=new HandlerData<T>();
        hd.httpCallBack=httpCallBack;
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                hd.e=e;
                mHandler.sendMessage(getMessage(ERROR_CODE,hd));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                char c = json.charAt(0);
                if (c!='<'){
                    T t = mGson.fromJson(json, classType);
                    hd.t=t;
                    mHandler.sendMessage(getMessage(SUCCESS_CODE,hd));
                }else{
                    hd.e=new IOException("没有数据！");
                    mHandler.sendMessage(getMessage(ERROR_CODE,hd));
                }
            }
        });
    }
    //拼接get请求的参数
    private String makeUrl(String url, Map<String,Object> map){
        if (map==null||map.size()==0){
            return url;
        }
        StringBuffer sb=new StringBuffer();
        for (String key:map.keySet()){
            sb.append(key+"="+map.get(key)+"&");
        }
        return url+sb.substring(0,sb.length()-1);
    }
    //得到Message对象
    private Message getMessage(int what,Object obj){
        Message message=Message.obtain();
        message.what=what;
        message.obj=obj;
        return message;
    }
    //回调接口
    class HandlerData<T>{
        HttpCallBack<T> httpCallBack;
        T t;
        IOException e;
        public void success(){
            if (httpCallBack!=null) {
                httpCallBack.onSuccess(t);
            }
        }
        public void error(){
            if (httpCallBack!=null){
                httpCallBack.onFailure(e);
            }
        }
    }


    //*************对外公布的方法************

    /**
     * 同步get请求
     * 返回response对象
     * @param url
     * @param map
     * @return
     * @throws IOException
     */
    public static Response getSyn(String url,Map<String,Object> map) throws IOException {
        return getInstance()._getSyn(url,map);
    }

    /**
     * 同步get请求
     * 返回实体类
     * @param url
     * @param map
     * @param classType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T getSynData(String url,Map<String,Object> map,Class classType) throws IOException {
        return (T) getInstance()._getSynData(url,map,classType);
    }

    /**
     * 异步get请求
     * 返回实体类
     * @param url
     * @param map
     * @param classType
     * @param httpCallBack
     */
    public static void getAsyn(String url,Map<String,Object> map,Class classType,HttpCallBack httpCallBack){
        getInstance()._getAsyn(url,map,classType,httpCallBack);
    }
}
