package com.bonuswallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.repository.OnRampRepositoryType;
import com.bonuswallet.app.router.MyAddressRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;

import io.reactivex.annotations.NonNull;

public class Erc20DetailViewModelFactory implements ViewModelProvider.Factory {

    private final MyAddressRouter myAddressRouter;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final OnRampRepositoryType onRampRepository;

    public Erc20DetailViewModelFactory(MyAddressRouter myAddressRouter,
                                       FetchTransactionsInteract fetchTransactionsInteract,
                                       AssetDefinitionService assetDefinitionService,
                                       TokensService tokensService,
                                       OnRampRepositoryType onRampRepository) {
        this.myAddressRouter = myAddressRouter;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
        this.onRampRepository = onRampRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new Erc20DetailViewModel(myAddressRouter, fetchTransactionsInteract, assetDefinitionService, tokensService, onRampRepository);
    }
}
