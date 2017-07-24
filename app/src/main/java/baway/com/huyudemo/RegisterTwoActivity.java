package baway.com.huyudemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import baway.com.huyudemo.bean.Register;
import baway.com.huyudemo.utils.ApiUtils;
import baway.com.huyudemo.utils.DialogUtils;
import baway.com.huyudemo.utils.HttpUtils;
import baway.com.huyudemo.utils.IntentAllUtils;
import baway.com.huyudemo.utils.NetUtil;

/**
 * description 注册的第二个页面
 * Created by 贾秀坤 on 2017/5/10.
 */

public class RegisterTwoActivity extends Activity implements View.OnClickListener {
    private EditText num;
    private EditText pwd;
    private Button login;
    private ImageView back;
    private TextView code;
    private String random;
    private IntentAllUtils instance;
    private ProgressBar bar;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_two);
        initView();
        instance = IntentAllUtils.getInstance();
        int random1=(int)(Math.random()*10);
        int random2=(int)(Math.random()*10);
        int random3=(int)(Math.random()*10);
        int random4=(int)(Math.random()*10);
        random = random1+""+random2+random3+random4;
        code.setText(random);
        back.setOnClickListener(this);
        login.setOnClickListener(this);

    }
    private void initView() {
        login = (Button) findViewById(R.id.reg_login);
        back = (ImageView) findViewById(R.id.reg_back);
        code= (TextView) findViewById(R.id.reg_code);
        num= (EditText) findViewById(R.id.reg_num);
        pwd= (EditText) findViewById(R.id.reg_pwd);
        bar = (ProgressBar) findViewById(R.id.register_two_bar);
        builder = DialogUtils.setDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_back:
                IntentAllUtils.getInstance().intent(this,RegisterActivity.class);
                finish();
                break;
            case R.id.reg_login:

                String codeNum = num.getText().toString().trim();
                String password = pwd.getText().toString().trim();
               if (random.equals(codeNum)){
                   if (NetUtil.isNetworkAvailable(RegisterTwoActivity.this)) {
                       bar.setVisibility(View.VISIBLE);
                       String tel = instance.getTel();
                       HttpUtils.getAsyn(ApiUtils.ZHUCE + "?phone=" + tel + "&password=" + password + "&postkey=1503d", null, Register.class, new HttpUtils.HttpCallBack<Register>() {
                           @Override
                           public void onSuccess(Register register) {
                               bar.setVisibility(View.GONE);
                               if (register.getRet_code() == 200) {
                                   Toast.makeText(RegisterTwoActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                   finish();
                               } else {
                                   Toast.makeText(RegisterTwoActivity.this, register.getRet_msg(), Toast.LENGTH_SHORT).show();
                               }
                           }

                           @Override
                           public void onFailure(IOException e) {

                           }
                       });
                   }else{
                       builder.show();
                   }

               }else{
                   Toast.makeText(this, "验证码输入错误,请重新输入", Toast.LENGTH_SHORT).show();
               }


                break;
            default:
                break;
        }

    }

}
