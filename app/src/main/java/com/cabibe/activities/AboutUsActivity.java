package com.cabibe.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;

import com.cabibe.R;
import com.cabibe.databinding.ActivityAboutUsBinding;

/**
 * Created by 128274 on 5/3/2018.
 */

public class AboutUsActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutUsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about_us);
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + actionBar.getTitle() + "</font>"));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static Intent newIntent(Context context)
    {
        Intent i = new Intent(context, AboutUsActivity.class);
        return i;
    }
}
