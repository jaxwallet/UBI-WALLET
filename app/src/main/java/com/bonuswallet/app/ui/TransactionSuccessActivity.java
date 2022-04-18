package com.bonuswallet.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.C;
import com.bonuswallet.app.R;
import com.bonuswallet.app.entity.StandardFunctionInterface;
import com.bonuswallet.app.viewmodel.TransactionSuccessViewModel;
import com.bonuswallet.app.viewmodel.TransactionSuccessViewModelFactory;
import com.bonuswallet.app.widget.CopyTextView;
import com.bonuswallet.app.widget.FunctionButtonBar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by JB on 4/12/2020.
 */
public class TransactionSuccessActivity extends BaseActivity implements StandardFunctionInterface
{
    private String transactionHash;
    private TextView tvDetail;

    @Inject
    TransactionSuccessViewModelFactory viewModelFactory;
    TransactionSuccessViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_success);

        transactionHash = getIntent().getStringExtra(C.EXTRA_TXHASH);
        CopyTextView hashText = findViewById(R.id.tx_hash);
        hashText.setText(transactionHash);

        String contractNet = getIntent().getStringExtra("contract_net");

        viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(TransactionSuccessViewModel.class);

        toolbar();

        setTitle(getString(R.string.empty));

        tvDetail = findViewById(R.id.text_detail);
        tvDetail.setText(getString(R.string.transaction_progress_detail, contractNet));

        FunctionButtonBar functionBar = findViewById(R.id.layoutButtons);
        functionBar.setupSecondaryFunction(this, R.string.action_show_tx_details);
//        functionBar.setupFunctions(this, new ArrayList<>(Collections.singletonList(R.string.action_show_tx_details)));
        functionBar.revealButtons();

        viewModel.tryToShowRateAppDialog(this);
    }

    @Override
    public void handleClick(String action, int actionId)
    {
        Intent intent = new Intent();
        intent.putExtra(C.EXTRA_TXHASH, transactionHash);
        setResult(RESULT_OK, intent);
        finish();
    }
}
