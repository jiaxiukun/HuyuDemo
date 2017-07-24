package baway.com.huyudemo.core.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 贾秀坤 on 2017/7/10.
 */

public interface BaseService {
    @GET
    Call<String> baseGetRequest(@Url String url);
}
