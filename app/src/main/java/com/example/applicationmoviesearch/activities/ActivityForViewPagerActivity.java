package com.example.applicationmoviesearch.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.adapters.VPAdapter;
import com.example.applicationmoviesearch.models.ViewPagerItem;

import java.util.ArrayList;

public class ActivityForViewPagerActivity extends AppCompatActivity {

   private ViewPager2 viewPager2;
   private  ArrayList<ViewPagerItem> viewPagerItemArrayList;
   private Button bntFinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        bntFinish = findViewById(R.id.button);
        bntFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPager2 = findViewById(R.id.viewpager);
        int[] images = {R.drawable.ic_full_star,R.drawable.ic_person_black_24dp,R.drawable.ic_baseline_warning_24};
        String[] heading = {"Intro","Demo","Fin"};
        String[] desc = {getString(R.string.star_desc),
                getString(R.string.person_desc),
                getString(R.string.end_desc)};
        viewPagerItemArrayList = new ArrayList<>();

        for (int i =0; i< images.length ; i++){
            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i],heading[i],desc[i]);
            viewPagerItemArrayList.add(viewPagerItem);
        }


        VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);

        viewPager2.setAdapter(vpAdapter);

        viewPager2.setClipToPadding(false);

        viewPager2.setClipChildren(false);

        viewPager2.setOffscreenPageLimit(2);

        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

    }










   /* private MainAdapter mainAdapter;
    private ViewPager2 viewPager2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_design);
        mainAdapter = new MainAdapter();
        viewPager2 = findViewById(R.id.view_pager2);
        viewPager2.setAdapter(mainAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("WELCOME");
                }else if(position == 1){
                    tab.setText("DIDACTICIEL");
                }else if(position == 2){
                    tab.setText("FIN");
                }
            }
        }).attach();
    }*/
}
