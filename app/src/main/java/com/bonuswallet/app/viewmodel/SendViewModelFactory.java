package com.bonuswallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.interact.CreateTransactionInteract;
import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.router.MyAddressRouter;
import com.bonuswallet.app.service.AnalyticsServiceType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.GasService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.TokensService;

import io.reactivex.annotations.NonNull;

public class SendViewModelFactory implements ViewModelProvider.Factory {

    private final MyAddressRouter myAddressRouter;
    private final EthereumNetworkRepositoryType networkRepository;
    private final TokensService tokensService;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final CreateTransactionInteract createTransactionInteract;
    private final GasService gasService;
    private final AssetDefinitionService assetDefinitionService;
    private final KeyService keyService;
    private final AnalyticsServiceType analyticsService;

    public SendViewModelFactory(MyAddressRouter myAddressRouter,
                                EthereumNetworkRepositoryType networkRepository,
                                TokensService tokensService,
                                FetchTransactionsInteract fetchTransactionsInteract,
                                CreateTransactionInteract createTransactionInteract,
                                GasService gasService,
                                AssetDefinitionService assetDefinitionService,
                                KeyService keyService,
                                AnalyticsServiceType analyticsService) {
        this.myAddressRouter = myAddressRouter;
        this.networkRepository = networkRepository;
        this.tokensService = tokensService;
        this.gasService = gasService;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.keyService = keyService;
        this.createTransactionInteract = createTransactionInteract;
        this.analyticsService = analyticsService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SendViewModel(myAddressRouter, networkRepository, tokensService,
                fetchTransactionsInteract, createTransactionInteract, gasService,
                assetDefinitionService, keyService, analyticsService);
    }
}
