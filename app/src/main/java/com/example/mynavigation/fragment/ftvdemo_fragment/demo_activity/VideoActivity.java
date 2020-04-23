package com.example.mynavigation.fragment.ftvdemo_fragment.demo_activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.mynavigation.R;

import me.yokeyword.fragmentation.SupportActivity;

public class VideoActivity extends SupportActivity {
    private int videoId;
    VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        initView();
        playVideo();
    }

    private void playVideo() {

//        设置屏幕横屏
        if(this.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        MediaController mediaController = new MediaController(this);

//        获取播放路径
        String uri = "android.resource://" + getPackageName() + "/" +videoId;
        videoView.setVideoURI(Uri.parse(uri));

//        关联播放器和控制器
        videoView.setMediaController(mediaController);

//        获取焦点
        videoView.requestFocus();
        videoView.start();
    }

    private void initView() {
        videoView = findViewById(R.id.videoView);
        videoId = getIntent().getIntExtra("videoId",0);
        Log.d("测试", "initView: "+videoId);
    }
}
