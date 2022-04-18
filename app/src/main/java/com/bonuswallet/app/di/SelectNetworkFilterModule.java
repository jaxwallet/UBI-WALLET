package com.bonuswallet.app.di;

import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.SelectNetworkFilterViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class SelectNetworkFilterModule {
    @Provides
    SelectNetworkFilterViewModelFactory provideSelectNetworkFilterViewModelFactory(EthereumNetworkRepositoryType networkRepositoryType,
                                                                                   TokensService tokensService,
                                                                                   PreferenceRepositoryType preferenceRepositoryType) {
        return new SelectNetworkFilterViewModelFactory(networkRepositoryType, tokensService, preferenceRepositoryType);
    }
}
