package baway.com.huyudemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.util.Map;

import baway.com.huyudemo.bean.Login;
import baway.com.huyudemo.utils.ApiUtils;
import baway.com.huyudemo.utils.DialogUtils;
import baway.com.huyudemo.utils.HttpUtils;
import baway.com.huyudemo.utils.IntentAllUtils;
import baway.com.huyudemo.utils.NetUtil;



public class LoginActivity extends Activity {

    private EditText num;
    private EditText pwd;
    private TextView register;
    private TextView forgetPwd;
    private Button login;
    private ProgressBar bar;
    private AlertDialog.Builder builder;
    private ImageView left_qzone;
    private UMShareAPI um;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();
        builder = DialogUtils.setDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               IntentAllUtils.getInstance().intent(LoginActivity.this,RegisterActivity.class);UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                System.out.println("share_media = " + share_media);
                //获取资料
                String uid = map.get("uid");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");
                ImageLoader.getInstance().displayImage(iconurl,left_qzone);

                System.out.println("uid = " + uid + ",name =" + name + ",gender =" + gender + "iconurl =" + iconurl);

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                System.out.println("onError SHARE_MEDIA share_media =" + share_media);

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                System.out.println("onCancel SHARE_MEDIA share_media = " + share_media);

            }
        });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetUtil.isNetworkAvailable(LoginActivity.this)) {
                    bar.setVisibility(View.VISIBLE);
                    String tel = num.getText().toString().trim();
                    String password = pwd.getText().toString().trim();
                    HttpUtils.getAsyn(ApiUtils.DENGLU + "?username=" + tel + "&password=" + password + "&postkey=1503d", null, Login.class, new HttpUtils.HttpCallBack<Login>() {
                        @Override
                        public void onSuccess(Login login) {
                            bar.setVisibility(View.GONE);
                            if (login.getRet_code() == 200) {
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, login.getRet_msg(), Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(IOException e) {

                        }
                    });
                }else{
                    builder.show();
                }
            }
        });

    }

    private void initView() {

        num = (EditText) findViewById(R.id.login_num);
        pwd = (EditText) findViewById(R.id.login_pwd);
        register = (TextView) findViewById(R.id.login_tv_register);
        forgetPwd = (TextView) findViewById(R.id.login_tv_forget_pwd);
        login = (Button) findViewById(R.id.login_bt);
        bar = (ProgressBar) findViewById(R.id.login_bar);
        left_qzone = (ImageView) findViewById(R.id.left_qzone);


    }
}
