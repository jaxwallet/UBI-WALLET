package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.TokenActivityViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TokenActivityModule {
    @Provides
    TokenActivityViewModelFactory provideTokenActivityViewModelFactory(AssetDefinitionService assetDefinitionService,
                                                                       FetchTransactionsInteract fetchTransactionsInteract,
                                                                       TokensService tokensService)
    {
        return new TokenActivityViewModelFactory(
                assetDefinitionService,
                fetchTransactionsInteract,
                tokensService);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepositoryType,
                                                               TokenRepositoryType tokenRepositoryType)
    {
        return new FetchTransactionsInteract(transactionRepositoryType, tokenRepositoryType);
    }
}
