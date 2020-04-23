package com.example.mynavigation.fragment.ftvdemo_fragment.demo_activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.mynavigation.R;
import com.example.mynavigation.utils.PictureTouch;

import me.yokeyword.fragmentation.SupportActivity;

public class PictureActivity extends SupportActivity {
    ImageView ivPicture;
    int pictureID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        initView();
        setPictureView();

    }

    private void setPictureView() {
        ivPicture.setImageResource(pictureID);
        ivPicture.setOnTouchListener(new PictureTouch());
    }


    private void initView() {
        ivPicture = findViewById(R.id.iv_picture);
        pictureID = getIntent().getIntExtra("pictureID",0);
        Log.d("测试", "initView: "+pictureID);
    }
}
