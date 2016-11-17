package com.yffsc.whxtest.HengYi;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yffsc.library.anim.ViewAnimationListenerAdapter;
import com.yffsc.library.transition.TransitionCompat;
import com.yffsc.library.transition.TransitionListenerAdapter;
import com.yffsc.whxtest.R;

import java.util.ArrayList;

public class HengYiMain extends Activity {

    MyViewPager myViewPager;
    ArrayList<View> views;
    MyViewPagerAdapter adapter;
    TextView tvSearch;
    TextView tvForMy;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heng_yi_main);
        tvForMy = (TextView) findViewById(R.id.tvForMy);
        tvForMy.setText("选择单据类型");
        myViewPager = (MyViewPager) findViewById(R.id.myPager);
        views = new ArrayList<>();
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        tvForMy = (TextView) findViewById(R.id.tvForMy);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isBack = true;
                tvSearch.setText("查单-");
                tvForMy.setText("选择单据类型");
                myViewPager.setCurrentItem(0);
            }
        });
        View view1 = View.inflate(this, R.layout.one, null);
        view1.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onePager(((Button) view).getText().toString());
            }
        });
        view1.findViewById(R.id.one1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onePager(((Button) view).getText().toString());
            }
        });
        view1.findViewById(R.id.one2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onePager(((Button) view).getText().toString());
            }
        });
        view1.findViewById(R.id.one3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onePager(((Button) view).getText().toString());
            }
        });
        views.add(view1);
        adapter = new MyViewPagerAdapter(views);
        myViewPager.setAdapter(adapter);
        // myViewPager.setPageTransformer(true, new ZoomOutPageTransformer());//动画
        /**
         * view动画的监听器，比如thumbNailScaleAnim，screenTransitAnim这样的动画就会在这里得到监听
         *
         * 这里的值是说明动画进行到什么时候，原始的view开始显示
         * 设置方式是：动画时间越长，可以设置的越精细，越靠近1，动画时间越短设置为0.95就差不多了
         * 这里的值请自行根据你的动画长度进行调整，如果调整不好可能会出现动画结束后相应元素不见的问题。
         * 这里测试是如果动画是2000ms，那么用0.998较为合适
         */
        final float fraction = 0.9f;
        TransitionCompat.addViewAnimListener(new ViewAnimationListenerAdapter() {
            boolean isShowed = false;

            @Override
            public void onViewAnimationStart(View view, Animator animator) {
                // TODO 自动生成的方法存根
                super.onViewAnimationStart(view, animator);
                if (MainActivity.isSceneAnim && TransitionCompat.isEnter) {
                    MainActivity.handler.sendEmptyMessage(123);
                }
            }

            public void onViewAnimationUpdate(View view, ValueAnimator valueAnimator, float progress) {
                super.onViewAnimationUpdate(view, valueAnimator, progress);
                // 判断当前是否是进入的状态，如果是进入的那么isEnter= true
                if (MainActivity.isSceneAnim && !TransitionCompat.isEnter
                        && progress >= fraction && !isShowed) {
                    MainActivity.handler.sendEmptyMessage(321);
                    isShowed = true;
                }
            }

            @Override
            public void onViewAnimationEnd(View view, Animator animator) {
                // TODO 自动生成的方法存根
                super.onViewAnimationEnd(view, animator);
                if (!TransitionCompat.isEnter && !isShowed) {
                    MainActivity.handler.sendEmptyMessage(321);
                    isShowed = true;
                }
            }
        });

        /**
         * 屏幕（场景）动画的监听器，这里用了适配器模式。可以传入完整的接口实现类
         */
        TransitionCompat.addListener(new TransitionListenerAdapter() {
            @Override
            public void onTransitionEnd(Animator animator, Animation animation,
                                        boolean isEnter) {
                super.onTransitionEnd(animator, animation, isEnter);
                // TODO:onEnd
            }
        });

//		TransitionCompat.setEnterTransition(new SceneFade(this, true));// use to scale Up animation
//		TransitionCompat.setAnimDuration(300);// default
//		TransitionCompat.setAnimStartDelay(0);// default
//		TransitionCompat.setTimeInterpolator(new AccelerateDecelerateInterpolator());// default
//		TransitionCompat.setAnimDuration(300);
        // 这段代码必须放在ActivityOptionsCompat各种设置之后
        TransitionCompat.startTransition(this, R.layout.activity_heng_yi_main);
    }


    boolean isBack = true;

    public void onePager(String string) {
        View view2 = View.inflate(HengYiMain.this, R.layout.two, null);
        tvForMy.setText("按" + string + "查询");
        view2.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvForMy.setText(tvForMy.getText().toString() + "-" + ((Button) view).getText());
                isBack = false;
                onBackPressed();
            }
        });
        view2.findViewById(R.id.two1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvForMy.setText(tvForMy.getText().toString() + "-" + ((Button) view).getText());
                isBack = false;
                onBackPressed();
            }
        });
        view2.findViewById(R.id.two2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvForMy.setText(tvForMy.getText().toString() + "-" + ((Button) view).getText());
                isBack = false;
                onBackPressed();
            }
        });
        view2.findViewById(R.id.two3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvForMy.setText(tvForMy.getText().toString() + "-" + ((Button) view).getText());
                isBack = false;
                onBackPressed();
            }
        });
        view2.findViewById(R.id.two4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvForMy.setText(tvForMy.getText().toString() + "-" + ((Button) view).getText());
                isBack = false;
                onBackPressed();
            }
        });
        views.add(view2);
        adapter.setmListViews(views);
        adapter.notifyDataSetChanged();
        myViewPager.setCurrentItem(views.size() - 1);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //TransitionCompat.setExitTransition(new MySceneAnim(this));//a test anim.Should not be use with customAnimation
        //TransitionCompat.setAnimStartDelay(0);// default
        //TransitionCompat.setAnimDuration(300);// default
        //TransitionCompat.setTimeInterpolator(new AccelerateDecelerateInterpolator());// default
        //TransitionCompat.finishAfterTransition(activity, enterAnim, exitAnim);// custom animation
        // 这段代码必须放在ActivityOptionsCompat各种设置之后

        TransitionCompat.setEndString(tvForMy.getText().toString());

        TransitionCompat.finishAfterTransition(this);//结束动画
        if (isBack) {
            setResult(5, new Intent().putExtra("title", "选择单据类型"));
        } else {
            setResult(5, new Intent().putExtra("title", tvForMy.getText().toString()));
        }
    }

}
