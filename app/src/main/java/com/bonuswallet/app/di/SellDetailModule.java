package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.CreateTransactionInteract;
import com.bonuswallet.app.interact.FindDefaultNetworkInteract;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.router.SellDetailRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.SellDetailModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by James on 22/02/2018.
 */

@Module
public class SellDetailModule {

    @Provides
    SellDetailModelFactory sellDetailModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            TokensService tokensService,
            CreateTransactionInteract createTransactionInteract,
            SellDetailRouter sellDetailRouter,
            KeyService keyService,
            AssetDefinitionService assetDefinitionService) {
        return new SellDetailModelFactory(
                findDefaultNetworkInteract, tokensService, createTransactionInteract, sellDetailRouter, keyService, assetDefinitionService);
    }

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType networkRepository) {
        return new FindDefaultNetworkInteract(networkRepository);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }

    @Provides
    SellDetailRouter provideSellDetailRouter() {
        return new SellDetailRouter();
    }
}
