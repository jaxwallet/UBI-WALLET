package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.router.MyAddressRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.Erc1155InfoViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc1155InfoModule {
    @Provides
    Erc1155InfoViewModelFactory provideErc1155InfoViewModelFactory(FetchTransactionsInteract fetchTransactionsInteract,
                                                                   AssetDefinitionService assetDefinitionService,
                                                                   TokensService tokensService)
    {
        return new Erc1155InfoViewModelFactory(
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter()
    {
        return new MyAddressRouter();
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepositoryType,
                                                               TokenRepositoryType tokenRepositoryType)
    {
        return new FetchTransactionsInteract(transactionRepositoryType, tokenRepositoryType);
    }
}
