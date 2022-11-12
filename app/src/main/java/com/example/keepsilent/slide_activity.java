package com.example.keepsilent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class slide_activity extends AppCompatActivity {
    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    Button backbtn,nextbtn,skipbtn;
    TextView[] dots;
    ViewPagerAdapter viewpageAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        nextbtn=findViewById(R.id.next);
        backbtn=findViewById(R.id.previous);
        skipbtn=findViewById(R.id.skip);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0)>0){
                    mSlideViewPager.setCurrentItem(getitem(-1),true);
                }

            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getitem(0)<2)
                    mSlideViewPager.setCurrentItem(getitem(1),true);
                else{
                    Intent i = new Intent(slide_activity.this,first_screen.class);
                    startActivity(i);
                    finish();
                }


            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(slide_activity.this,first_screen.class);
                startActivity(i);
                finish();

            }
        });

        mSlideViewPager =(ViewPager) findViewById(R.id.viewPager);
        mDotLayout= (LinearLayout) findViewById(R.id.indicator_layout);

        viewpageAdaptor= new ViewPagerAdapter(this);
        mSlideViewPager.setAdapter(viewpageAdaptor);
        setUpindicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListner);
    }

    public  void  setUpindicator(int position){
        dots= new TextView[3];
        mDotLayout.removeAllViews();

        for(int i=0;i<dots.length; i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].getTextSize();
            mDotLayout.addView(dots[i]);



        }
        dots[position].setTextColor(getResources().getColor(R.color.purple_200));
    }

    ViewPager.OnPageChangeListener viewListner= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);
            if(position >0){
                backbtn.setVisibility(View.VISIBLE);
            }else{
                backbtn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){
        return mSlideViewPager.getCurrentItem() + i;
    }
}