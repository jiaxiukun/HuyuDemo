package baway.com.huyudemo.utils;


import android.content.Context;
import android.content.Intent;


/**
 * description 跳转的工具类
 * Created by 贾秀坤 on 2017/4/19.
 */

public class IntentAllUtils {
    private Object object;
    private String tel;
    private  String url;
    private volatile static IntentAllUtils intentAllUtils;


    public static IntentAllUtils getInstance() {
        if (intentAllUtils == null) {
            synchronized (IntentAllUtils.class) {
                if (intentAllUtils == null) {
                    intentAllUtils = new IntentAllUtils();
                }
            }
        }
        return intentAllUtils;
    }

    private IntentAllUtils() {
    }

    public void intentAll(Context context, Class classType, Object object) {
        Intent intent = new Intent(context, classType);
        this.object = object;
        context.startActivity(intent);
    }

    public void intentTel(Context context, Class classType, String tel) {
        Intent intent = new Intent(context, classType);
        this.tel = tel;
        context.startActivity(intent);
    }
    public void intentUrl(Context context, Class classType, String url) {
        Intent intent = new Intent(context, classType);
        this.url = url;
        context.startActivity(intent);
    }
    public void intent(Context context, Class classType) {
        Intent intent = new Intent(context, classType);
        context.startActivity(intent);
    }

    public Object getObject() {
        return object;
    }

    public String getTel() {
        return tel;
    }

    public String getUrl() {
        return url;
    }
}
