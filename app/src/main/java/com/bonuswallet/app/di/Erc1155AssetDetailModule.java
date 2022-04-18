package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.Erc1155AssetDetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc1155AssetDetailModule {
    @Provides
    Erc1155AssetDetailViewModelFactory provideErc1155AssetDetailViewModelFactory(
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            GenericWalletInteract walletInteract)
    {
        return new Erc1155AssetDetailViewModelFactory(assetDefinitionService, tokensService, walletInteract);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository)
    {
        return new GenericWalletInteract(walletRepository);
    }
}
