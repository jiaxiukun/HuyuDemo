package baway.com.huyudemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import baway.com.huyudemo.utils.IntentAllUtils;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText num;
    private TextView login;
    private Button next;
    private ImageView back;
    private CheckBox box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
        back.setOnClickListener(this);
        next.setOnClickListener(this);
        login.setOnClickListener(this);
    }
    private void initView() {
        num = (EditText) findViewById(R.id.register_num);
        login = (TextView) findViewById(R.id.login);
        next = (Button) findViewById(R.id.register_next);
        back = (ImageView) findViewById(R.id.register_back);
        box= (CheckBox) findViewById(R.id.register_cb);
        SpannableStringBuilder style = new SpannableStringBuilder(box.getText().toString());
        style.setSpan(new ForegroundColorSpan(Color.BLUE), 3, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        box.setText(style);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_back:
                IntentAllUtils.getInstance().intent(RegisterActivity.this,LoginActivity.class);
                finish();
                break;
            case R.id.register_next:
                if (box.isChecked()){
                    IntentAllUtils.getInstance().intentTel(this,RegisterTwoActivity.class,num.getText().toString().trim());
                    finish();
                }else{
                    Toast.makeText(this, "亲,请接受协议哦!", Toast.LENGTH_SHORT).show();
                }

                break;
            case  R.id.login:
                IntentAllUtils.getInstance().intent(this,LoginActivity.class);
                finish();
                break;
            default:
                break;
        }

    }
}
