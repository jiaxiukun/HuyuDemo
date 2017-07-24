package baway.com.huyudemo.home.model;

import baway.com.huyudemo.core.net.CallbackListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 贾秀坤 on 2017/7/10.
 */

public class HomeService {
    public static volatile HomeService instance;

    public HomeService() {
    }

    public static HomeService getInstance() {
        if (instance != null) {
            synchronized (HomeService.class) {
                if (instance != null) {
                    instance = new HomeService();
                }
            }
        }
        return instance;
    }

    //获取配置数据
    public void getConfig(String format, String cityname, String key, final CallbackListener callbackListener) {
        callbackListener.onNetStart();
        Call<String> call = ApiHome.getInstance().getConfigFromServer(format, cityname, key);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && response.isSuccessful() && response.body() != null && response.body().length() > 0) {
                    callbackListener.onNetSuccess(ConfigResponse.objectFromData(response.body().toString()));
                } else {
                    callbackListener.onNetFail("");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callbackListener.onNetFail("");
            }
        });
    }
}
