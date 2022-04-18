package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.Erc1155AssetSelectViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc1155AssetSelectModule {
    @Provides
    Erc1155AssetSelectViewModelFactory provideErc1155ViewModelFactory(FetchTransactionsInteract fetchTransactionsInteract,
                                                                      AssetDefinitionService assetDefinitionService,
                                                                      TokensService tokensService)
    {
        return new Erc1155AssetSelectViewModelFactory(
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepositoryType,
                                                               TokenRepositoryType tokenRepositoryType)
    {
        return new FetchTransactionsInteract(transactionRepositoryType, tokenRepositoryType);
    }
}
