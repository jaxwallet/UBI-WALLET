package com.bonuswallet.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bonuswallet.app.R;
import com.bonuswallet.app.entity.StandardFunctionInterface;
import com.bonuswallet.app.widget.FunctionButtonBar;

import java.util.ArrayList;
import java.util.Collections;

import dagger.android.AndroidInjection;

public class KycVerificationActivity extends BaseActivity implements View.OnClickListener, StandardFunctionInterface {

    private TextView tvCaption, tvTitle, tvsubTitle, tvName;
    private LinearLayout content;
    private FunctionButtonBar functionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_verification);
        toolbar();
        setTitle(getString(R.string.title_kyc_verification));
        initViews();
    }

    private void initViews() {
        tvCaption = findViewById(R.id.tv_kyc_caption);
        tvTitle = findViewById(R.id.tv_kyc_title);
        tvsubTitle = findViewById(R.id.tv_kyc_subtitle);
        tvName = findViewById(R.id.tv_kyc_name);
        content = findViewById(R.id.ll_kyc_content);
        functionBar = findViewById(R.id.layoutButtons);
        functionBar.setPrimaryButtonText(R.string.action_start_verification);
        functionBar.setPrimaryButtonClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}