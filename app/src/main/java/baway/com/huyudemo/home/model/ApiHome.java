package baway.com.huyudemo.home.model;

import baway.com.huyudemo.Constans;
import baway.com.huyudemo.core.net.ApiGenerator;
import baway.com.huyudemo.core.net.BaseService;
import retrofit2.Call;

/**
 * Created by 贾秀坤 on 2017/7/10.
 */

public class ApiHome {
    public static volatile ApiHome instance;
    private BaseService mBaseService;

    public ApiHome(){
        mBaseService= ApiGenerator.getBaseNetService();
    }
    public static ApiHome getInstance(){
        if (instance==null){
            synchronized (ApiHome.class){
                if (instance==null){
                    instance=new ApiHome();
                }
            }
        }
        return instance;
    }
    public Call<String> getConfigService(String format,String cityname,String key){

        if (mBaseService==null){
            mBaseService=ApiGenerator.getBaseNetService();
        }
        return ApiGenerator.getBaseNetService().baseGetRequest(getConfigUrl(format,cityname,key));
    }
    public String getConfigUrl(String format, String cityname, String key) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Constans.Base.baseUrl).append("weather/index?format=").append(format).append("&cityname=").append(cityname).append("&key=").append(key);
        return stringBuffer.toString();
    }
    public Call<String> getConfigFromServer(String format, String cityname, String key){
        return ApiGenerator.getBaseNetService().baseGetRequest(getConfigUrl(format, cityname, key));
    }
}
