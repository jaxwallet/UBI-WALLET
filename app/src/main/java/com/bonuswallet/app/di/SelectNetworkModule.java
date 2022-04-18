package com.bonuswallet.app.di;

import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.SelectNetworkViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class SelectNetworkModule {
    @Provides
    SelectNetworkViewModelFactory provideSelectNetworkViewModelFactory(EthereumNetworkRepositoryType networkRepositoryType,
                                                                       TokensService tokensService,
                                                                       PreferenceRepositoryType preferenceRepositoryType)
    {
        return new SelectNetworkViewModelFactory(networkRepositoryType, tokensService, preferenceRepositoryType);
    }
}
