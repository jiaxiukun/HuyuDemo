package baway.com.huyudemo;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.VideoView;

/**
 * 视频播放，以为手机大小不同，原生的videoview不能全屏实现
 * Created by 贾秀坤 on 2017/7/6.
 */

public class Myvideoview extends VideoView{
    public Myvideoview(Context context) {
        super(context);
    }

    public Myvideoview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Myvideoview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //重新计算 高度
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);

    }

    @Override
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener l) {
        super.setOnPreparedListener(l);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
