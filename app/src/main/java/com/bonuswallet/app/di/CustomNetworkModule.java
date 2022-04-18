package com.bonuswallet.app.di;

import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.viewmodel.CustomNetworkViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class CustomNetworkModule {
    @Provides
    CustomNetworkViewModelFactory provideSelectNetworkViewModelFactory(EthereumNetworkRepositoryType networkRepositoryType)
    {
        return new CustomNetworkViewModelFactory(networkRepositoryType);
    }
}
