package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.CreateTransactionInteract;
import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.AnalyticsServiceType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.GasService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.TransferTicketDetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by James on 22/02/2018.
 */

@Module
public class TransferTicketDetailModule {

    @Provides
    TransferTicketDetailViewModelFactory transferTicketDetailViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            KeyService keyService,
            CreateTransactionInteract createTransactionInteract,
            FetchTransactionsInteract fetchTransactionsInteract,
            AssetDefinitionService assetDefinitionService,
            GasService gasService,
            AnalyticsServiceType analyticsService,
            TokensService tokensService) {
        return new TransferTicketDetailViewModelFactory(genericWalletInteract,
                keyService,
                createTransactionInteract,
                fetchTransactionsInteract,
                assetDefinitionService,
                gasService,
                analyticsService,
                tokensService);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }
    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepository,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepository, tokenRepositoryType);
    }
}