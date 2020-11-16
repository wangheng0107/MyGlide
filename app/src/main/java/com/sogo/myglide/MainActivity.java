package com.sogo.myglide;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sogo.myglide.request.Glide;
import com.sogo.myglide.request.RequestListener;

/**
 * @author wangheng
 */
public class MainActivity extends AppCompatActivity {
//    String url = "https://seopic.699pic.com/photo/50110/9774.jpg_wh1200.jpg";
    String[] arrayUrl = new String[]{
            "https://seopic.699pic.com/photo/50110/9774.jpg_wh1200.jpg",
            "https://seopic.699pic.com/photo/40008/0667.jpg_wh1200.jpg",
            "https://seopic.699pic.com/photo/50080/9948.jpg_wh1200.jpg",
            "https://seopic.699pic.com/photo/50168/6701.jpg_wh1200.jpg",
            "https://seopic.699pic.com/photo/50168/9251.jpg_wh1200.jpg",
            "https://seopic.699pic.com/photo/50059/7719.jpg_wh1200.jpg",
            "https://seopic.699pic.com/photo/50061/8976.jpg_wh1200.jpg",
            "https://seopic.699pic.com/photo/50163/0488.jpg_wh1200.jpg",
            "https://seopic.699pic.com/photo/50155/3006.jpg_wh1200.jpg",
            "https://seopic.699pic.com/photo/50047/9336.jpg_wh1200.jpg"
    };

    private LinearLayout scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView addImage = (TextView) findViewById(R.id.add_image);
        scrollView = findViewById(R.id.linearlayout);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.removeAllViews();
                moreImage();
            }
        });

    }

    private void moreImage(){
        for (int i=0;i<arrayUrl.length;i++){
            TextView textView = new TextView(this);
            ImageView imageView = new ImageView(this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            textView.setGravity(Gravity.CENTER);
            textView.setText(i+"");
            scrollView.addView(imageView);
            scrollView.addView(textView);
            Glide.with(MainActivity.this).load(arrayUrl[i]).loading(R.drawable.progress_more_loading).listener(new RequestListener() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    Log.e("MainActivity","--------------->加载成功!");

                }

                @Override
                public void onFailed() {

                }
            }).into(imageView);
        }

    }
}
