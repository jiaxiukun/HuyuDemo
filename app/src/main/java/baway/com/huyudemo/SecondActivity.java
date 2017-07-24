package baway.com.huyudemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mSearch;
    private EditText mEditText;
    private ImageView yuyin_img;
    private ImageView image_login;
    private UMShareAPI um;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        um = UMShareAPI.get(this);
        setContentView(R.layout.activity_second);
        initView();

        SpeechUtility.createUtility(SecondActivity.this, SpeechConstant.APPID + "= 59642f56");
        yuyin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSpeech(SecondActivity.this);
            }
        });
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://www.baidu.com/#wd=" + mEditText.getText());
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

        Intent intent=new Intent(SecondActivity.this,LoginActivity.class);
        startActivity(intent);
//        UMShareAPI.get(SecondActivity.this).getPlatformInfo(SecondActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
//            @Override
//            public void onStart(SHARE_MEDIA share_media) {
//
//            }
//
//            @Override
//            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//
//                System.out.println("share_media = " + share_media);
//                //获取资料
//                String uid = map.get("uid");
//                String name = map.get("name");
//                String gender = map.get("gender");
//                String iconurl = map.get("iconurl");
//                ImageLoader.getInstance().displayImage(iconurl, image_login);
//
//                System.out.println("uid = " + uid + ",name =" + name + ",gender =" + gender + "iconurl =" + iconurl);
//
//            }
//
//            @Override
//            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//
//                System.out.println("onError SHARE_MEDIA share_media =" + share_media);
//
//            }
//
//            @Override
//            public void onCancel(SHARE_MEDIA share_media, int i) {
//                System.out.println("onCancel SHARE_MEDIA share_media = " + share_media);
//
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        um.onActivityResult(requestCode,resultCode,data);
    }

    private void initView() {
       mSearch = (ImageView) findViewById(R.id.image_sou);
        mEditText = (EditText) findViewById(R.id.edittext_search);
        yuyin_img = (ImageView) findViewById(R.id.image_yuyin);
        image_login = (ImageView) findViewById(R.id.image_login);

        image_login.setOnClickListener(this);
    }

    public void initSpeech(final Context context) {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(context, null);
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                if (!isLast) {
                    //解析语音
                    String result = parseVoice(recognizerResult.getResultString());
                    mEditText.setText(result);

                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();
    }

    /**
     * 解析语音json
     */
    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);

        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean> ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }

    /**
     * 语音对象封装
     */
    public class Voice {

        public ArrayList<WSBean> ws;

        public class WSBean {
            public ArrayList<CWBean> cw;
        }

        public class CWBean {
            public String w;
        }
    }

}

