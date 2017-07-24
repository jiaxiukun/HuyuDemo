package baway.com.huyudemo.home.presenter;

/**
 * Created by 贾秀坤 on 2017/7/10.
 */

public interface IHomePresenter {
    void loadData(String format,String cityname,String key);
    void detachView();
}
