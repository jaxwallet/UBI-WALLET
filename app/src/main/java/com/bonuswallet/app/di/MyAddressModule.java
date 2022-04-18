package com.bonuswallet.app.di;

import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.MyAddressViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class MyAddressModule {
    @Provides
    MyAddressViewModelFactory provideMyAddressViewModelFactory(
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokensService tokensService,
            AssetDefinitionService assetDefinitionService) {
        return new MyAddressViewModelFactory(
                ethereumNetworkRepository,
                tokensService,
                assetDefinitionService);
    }
}
