package cn.com.cienet.lenovoyxt.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.com.cienet.lenovoyxt.R;


public class MainActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityCreate() {
        imageView = (ImageView) findViewById(R.id.iv);
        Glide.with(MainActivity.this).load("http://e.hiphotos.baidu.com/image/pic/item/2fdda3cc7cd98d10b510fdea233fb80e7aec9021.jpg").placeholder(R.mipmap.ic_launcher).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialogUtils.showLoading(true);
                changeAc(TestActivity.class);
            }
        });
    }
}
