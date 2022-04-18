package com.bonuswallet.app.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.C;
import com.bonuswallet.app.R;
import com.bonuswallet.app.entity.Wallet;
import com.bonuswallet.app.entity.tokens.Token;
import com.bonuswallet.app.ui.widget.adapter.ActivityAdapter;
import com.bonuswallet.app.viewmodel.TokenActivityViewModel;
import com.bonuswallet.app.viewmodel.TokenActivityViewModelFactory;
import com.bonuswallet.app.widget.ActivityHistoryList;
import com.bonuswallet.ethereum.EthereumNetworkBase;

import java.math.BigInteger;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class TokenActivityFragment extends BaseFragment {
    @Inject
    TokenActivityViewModelFactory viewModelFactory;

    private TokenActivityViewModel viewModel;

    private ActivityHistoryList history;

    private Wallet wallet;

    private Token token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        AndroidSupportInjection.inject(this);
        return inflater.inflate(R.layout.fragment_token_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null)
        {
            viewModel = new ViewModelProvider(this, viewModelFactory)
                    .get(TokenActivityViewModel.class);

            long chainId = getArguments().getLong(C.EXTRA_CHAIN_ID, EthereumNetworkBase.MAINNET_ID);
            token = viewModel.getTokensService().getToken(chainId, getArguments().getString(C.EXTRA_ADDRESS));
            wallet = getArguments().getParcelable(C.Key.WALLET);

            history = view.findViewById(R.id.history_list);

            setUpRecentTransactionsView();
        }
    }

    private void setUpRecentTransactionsView()
    {
        ActivityAdapter adapter = new ActivityAdapter(viewModel.getTokensService(), viewModel.getTransactionsInteract(),
                viewModel.getAssetDefinitionService());
        adapter.setDefaultWallet(wallet);
        history.setupAdapter(adapter);
        history.startActivityListeners(viewModel.getRealmInstance(wallet), wallet,
                token, viewModel.getTokensService(), BigInteger.ZERO, 15);
    }
}
