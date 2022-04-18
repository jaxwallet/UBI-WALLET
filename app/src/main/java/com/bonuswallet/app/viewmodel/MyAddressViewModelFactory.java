package com.bonuswallet.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;

public class MyAddressViewModelFactory implements ViewModelProvider.Factory {
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;
    private final TokensService tokensService;
    private final AssetDefinitionService assetDefinitionService;

    public MyAddressViewModelFactory(
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokensService tokensService,
            AssetDefinitionService assetDefinitionService) {
        this.ethereumNetworkRepository = ethereumNetworkRepository;
        this.tokensService = tokensService;
        this.assetDefinitionService = assetDefinitionService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MyAddressViewModel(
                ethereumNetworkRepository,
                tokensService,
                assetDefinitionService
                );
    }
}
