package baway.com.huyudemo.home.presenter;

import baway.com.huyudemo.core.net.CallbackListener;
import baway.com.huyudemo.home.model.ConfigResponse;
import baway.com.huyudemo.home.model.HomeService;
import baway.com.huyudemo.home.view.IHomeView;

/**
 * Created by 贾秀坤 on 2017/7/10.
 */

public class HomePresenterImpl implements IHomePresenter {

    private IHomeView homeView;
    public HomePresenterImpl() {
    }

    public HomePresenterImpl(IHomeView homeView) {

        this.homeView=homeView;
    }

    @Override
    public void loadData(String format, String  cityname,String key) {
        HomeService.getInstance().getConfig(format, cityname,key,new CallbackListener<ConfigResponse>() {
            @Override
            public void onNetStart() {

            }

            @Override
            public void onNetSuccess(ConfigResponse configResponse) {
                if (homeView != null) {
                    if (configResponse != null) {
                        homeView.refreshView(configResponse);
                    } else {
                        homeView.showOrHideErrorView(true);
                    }
                }
            }

            @Override
            public void onNetFail(ConfigResponse configResponse) {
                showOrHiderProgress(false);
            }
        });
    }
    private void showOrHiderProgress(boolean flag) {
        if (homeView != null) {
            homeView.showOrHideErrorView(flag);
        }
    }


    @Override
    public void detachView() {
        if(homeView!=null){
            homeView=null;
        }
    }
}
