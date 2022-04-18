package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.CreateTransactionInteract;
import com.bonuswallet.app.interact.FindDefaultNetworkInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.AnalyticsServiceType;
import com.bonuswallet.app.service.GasService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.RealmManager;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.WalletConnectViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class WalletConnectModule {
    @Provides
    WalletConnectViewModelFactory provideWalletConnectViewModelFactory(
            KeyService keyService,
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            CreateTransactionInteract createTransactionInteract,
            GenericWalletInteract genericWalletInteract,
            RealmManager realmManager,
            GasService gasService,
            TokensService tokensService,
            AnalyticsServiceType analyticsServiceType) {
        return new WalletConnectViewModelFactory(
                keyService,
                findDefaultNetworkInteract,
                createTransactionInteract,
                genericWalletInteract,
                realmManager,
                gasService,
                tokensService,
                analyticsServiceType);
    }

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType ethereumNetworkRepositoryType) {
        return new FindDefaultNetworkInteract(ethereumNetworkRepositoryType);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }

    @Provides
    GenericWalletInteract provideGenericWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }
}
