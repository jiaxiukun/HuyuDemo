package baway.com.huyudemo.core.net;

import baway.com.huyudemo.Constans;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 贾秀坤 on 2017/7/10.
 */

public class ApiGenerator {

    static Retrofit retrofit=new Retrofit.Builder().baseUrl(Constans.Base.baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create()).build();

    public static BaseService  getBaseNetService(){
        return retrofit.create(BaseService.class);
    }
}
