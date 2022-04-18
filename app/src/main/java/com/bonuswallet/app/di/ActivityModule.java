package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.service.TransactionsService;
import com.bonuswallet.app.viewmodel.ActivityViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JB on 26/06/2020.
 */
@Module
class ActivityModule
{
    @Provides
    ActivityViewModelFactory provideActivityViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            FetchTransactionsInteract fetchTransactionsInteract,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            TransactionsService transactionsService) {
        return new ActivityViewModelFactory(
                genericWalletInteract,
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService,
                transactionsService);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepository,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepository, tokenRepositoryType);
    }
}
