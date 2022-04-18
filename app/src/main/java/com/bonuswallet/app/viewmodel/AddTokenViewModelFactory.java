package com.bonuswallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.bonuswallet.app.interact.FetchTokensInteract;
import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;

public class AddTokenViewModelFactory implements ViewModelProvider.Factory {

    private final GenericWalletInteract genericWalletInteract;
    private final FetchTokensInteract fetchTokensInteract;
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final PreferenceRepositoryType sharedPreference;

    public AddTokenViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            FetchTokensInteract fetchTokensInteract,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            FetchTransactionsInteract fetchTransactionsInteract,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            PreferenceRepositoryType sharedPreference) {
        this.genericWalletInteract = genericWalletInteract;
        this.fetchTokensInteract = fetchTokensInteract;
        this.ethereumNetworkRepository = ethereumNetworkRepository;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
        this.sharedPreference = sharedPreference;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTokenViewModel(
                genericWalletInteract,
                fetchTokensInteract,
                ethereumNetworkRepository,
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService,
                sharedPreference);
    }
}
