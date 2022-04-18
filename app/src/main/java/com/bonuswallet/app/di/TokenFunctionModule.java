package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.CreateTransactionInteract;
import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.router.TransferTicketDetailRouter;
import com.bonuswallet.app.service.AnalyticsServiceType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.GasService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.OpenSeaService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.TokenFunctionViewModelFactory;

import dagger.Module;
import dagger.Provides;
/**
 * Created by James on 2/04/2019.
 * AJ TECHNOLOGIES LTD
 */

@Module
public class TokenFunctionModule
{
    @Provides
    TokenFunctionViewModelFactory provideTokenFunctionViewModelFactory(
            AssetDefinitionService assetDefinitionService,
            CreateTransactionInteract createTransactionInteract,
            GasService gasService,
            TokensService tokensService,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            KeyService keyService,
            GenericWalletInteract genericWalletInteract,
            OpenSeaService openseaService,
            FetchTransactionsInteract fetchTransactionsInteract,
            AnalyticsServiceType analyticsServiceType) {

        return new TokenFunctionViewModelFactory(
                assetDefinitionService, createTransactionInteract, gasService, tokensService, ethereumNetworkRepository, keyService, genericWalletInteract, openseaService, fetchTransactionsInteract, analyticsServiceType);
    }

    @Provides
    TransferTicketDetailRouter provideTransferTicketRouter() {
        return new TransferTicketDetailRouter();
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }

    @Provides
    GenericWalletInteract provideGenericWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepository,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepository, tokenRepositoryType);
    }
}
