package com.tts.www.tsg_disaster_response_app.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tts.www.tsg_disaster_response_app.R;
import com.tts.www.tsg_disaster_response_app.adapters.DemoPagerAdapter;

public class SplashScreen extends BaseActivity implements ViewPager.OnPageChangeListener {

    private int[] resources ={R.drawable.responsefirst,R.drawable.responsesecond,R.drawable.responsethird,R.drawable.responsefifth};
    private Handler handler;
    private ViewPager viewPager;
    private ImageView ivIndi;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Session();
            }
        }, 4100);

        viewPager = findViewById(R.id.vpDemo);
        ivIndi = findViewById(R.id.ivIndi);

    //    viewPager.setAdapter(new );
        viewPager.setAdapter(new DemoPagerAdapter(this, resources, null, new DemoPagerAdapter.CallbackDemoPagerAdapter() {
            @Override
            public void skipDemo() {
                startActivity(new Intent(SplashScreen.this,MainPage.class));
                finish();
            }
        }));
        viewPager.addOnPageChangeListener(this);
        handler.postDelayed(nextPagerSwitcher, 1000);
    }

    private Runnable nextPagerSwitcher = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 1000);
            viewPager.setCurrentItem(i,true);
            i++;
            if(i > 3){
                i = 0;
            }

        }
    };

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                ivIndi.setImageResource(R.drawable.indi_1);
                break;
            case 1:
                ivIndi.setImageResource(R.drawable.indi_2);
                break;
            case 2:
                ivIndi.setImageResource(R.drawable.indi_3);
                break;
            case 3:
                ivIndi.setImageResource(R.drawable.indi_1);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public void Session(){
        if(app.isSession()){
            IntentWithClosePrevious(MainPage.class);
        } else{
            IntentWithClosePrevious(LoginActivity.class);
        }
    }
}
