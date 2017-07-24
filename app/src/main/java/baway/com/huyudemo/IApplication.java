package baway.com.huyudemo;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by 贾秀坤 on 2017/7/11.
 */

public class IApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        Config.DEBUG = true;
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106031063", "cqbtzvAp6NRioSBT");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(configuration);

        //初始化全局异常类
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        if (LeakCanary.isInAnalyzerProcess(this)){
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

    }

}
