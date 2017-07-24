package baway.com.huyudemo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static baway.com.huyudemo.R.id.videoview;

public class MainActivity extends Activity {


    private Myvideoview mvideoview;
    private int recLen = 5;
    private TextView txtView;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据源
        initView();
        timer.schedule(task, 1000, 1000);
}

    /**
     * 初始化
     */
    private void initView() {
        mvideoview = (Myvideoview) findViewById(videoview);
        txtView = (TextView) findViewById(R.id.tv_start);
        //设置播放加载路径
        mvideoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.aa));
        //播放
        mvideoview.start();
        //循环播放
        mvideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mvideoview.start();
            }
        });

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    txtView.setText(recLen + "秒");
                    if (recLen == 0) {
                        timer.cancel();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    };



}
