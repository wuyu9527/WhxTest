package com.yffsc.whxtest.HengYi;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yffsc.library.ActivityCompatICS;
import com.yffsc.library.ActivityOptionsCompatICS;
import com.yffsc.library.transition.TransitionCompat;
import com.yffsc.whxtest.R;

public class MainActivity extends Activity {

    // 通过handler来相应动画操作，进行动画元素的恢复
    public static MyHandler handler;

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 123) {

                orginalTextView.setVisibility(View.INVISIBLE);

            } else if (msg.what == 321) {

                orginalTextView.setVisibility(View.VISIBLE);

                isSceneAnim = false;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orginalTextView= (Button) findViewById(R.id.btR);
        intent=new Intent(this,HengYiMain.class);
        handler = new MyHandler();
        findViewById(R.id.btR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionCompat.setEndString(((Button)view).getText().toString());
                screenTransitAnimByPair(Pair.create((View)orginalTextView, R.id.tvForMy));
            }
        });
    }

    /**
     * 这里可以用多个元素或者是单个元素进行动画，这里用了多个元素。为了保证动画效果，这里进行了渐变操作，
     * 在handle中进行了恢复操作，这样让动画看起来平滑了很多
     * @param views
     */
    @SuppressWarnings("unchecked")
    public void screenTransitAnimByPair(Pair<View, Integer>... views) {
        isSceneAnim = true;
        ActivityOptionsCompatICS options = ActivityOptionsCompatICS.makeSceneTransitionAnimation(
                MainActivity.this, views);
        ActivityCompatICS.startActivity(MainActivity.this, intent, options.toBundle());
    }

    public static boolean isSceneAnim = false;


    private Button orginalTextView;

    private Intent intent;
    // 通过handler来相应动画操作，进行动画元素的恢复


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ActivityOptionsCompatICS.RESULT_CODE){
            if (resultCode==5){
                orginalTextView.setText(data.getStringExtra("title"));
            }
        }
    }
}
