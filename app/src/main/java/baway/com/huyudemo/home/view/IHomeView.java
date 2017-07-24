package baway.com.huyudemo.home.view;

/**
 * Created by 贾秀坤 on 2017/7/10.
 */

public interface IHomeView<T>{
    void showOrHideProgress(boolean flag);
    void showOrHideErrorView(boolean flag);
    void refreshView(T t);


}
