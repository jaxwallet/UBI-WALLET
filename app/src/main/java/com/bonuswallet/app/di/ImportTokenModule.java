package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.CreateTransactionInteract;
import com.bonuswallet.app.interact.FetchTokensInteract;
import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.AlphaWalletService;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.GasService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.ImportTokenViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by James on 9/03/2018.
 */

@Module
public class ImportTokenModule {

    @Provides
    ImportTokenViewModelFactory importTokenViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            CreateTransactionInteract createTransactionInteract,
            FetchTokensInteract fetchTokensInteract,
            TokensService tokensService,
            AlphaWalletService alphaWalletService,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            AssetDefinitionService assetDefinitionService,
            FetchTransactionsInteract fetchTransactionsInteract,
            GasService gasService,
            KeyService keyService) {
        return new ImportTokenViewModelFactory(
                genericWalletInteract, createTransactionInteract, fetchTokensInteract, tokensService, alphaWalletService, ethereumNetworkRepository, assetDefinitionService, fetchTransactionsInteract, gasService, keyService);
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
    FetchTokensInteract provideFetchTokensInteract(TokenRepositoryType tokenRepository) {
        return new FetchTokensInteract(tokenRepository);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepository, TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepository, tokenRepositoryType);
    }
}
