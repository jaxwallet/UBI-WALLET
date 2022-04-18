package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.router.TransferTicketDetailRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.TransferTicketViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by James on 16/02/2018.
 */

@Module
public class TransferTicketModule
{
    @Provides
    TransferTicketViewModelFactory transferTicketViewModelFactory(
            TokensService tokensService,
            GenericWalletInteract genericWalletInteract,
            TransferTicketDetailRouter transferTicketDetailRouter,
            AssetDefinitionService assetDefinitionService) {
        return new TransferTicketViewModelFactory(
                tokensService, genericWalletInteract, transferTicketDetailRouter, assetDefinitionService);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    TransferTicketDetailRouter provideTransferTicketDetailRouter() {
        return new TransferTicketDetailRouter();
    }
}

