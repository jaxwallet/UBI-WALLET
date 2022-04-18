package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.router.RedeemSignatureDisplayRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.RedeemAssetSelectViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by James on 27/02/2018.
 */

@Module
public class RedeemAssetSelectModule
{
    @Provides
    RedeemAssetSelectViewModelFactory redeemTokenSelectViewModelFactory(
            RedeemSignatureDisplayRouter redeemSignatureDisplayRouter,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            GenericWalletInteract genericWalletInteract) {

        return new RedeemAssetSelectViewModelFactory(redeemSignatureDisplayRouter, assetDefinitionService, tokensService, genericWalletInteract);
    }

    @Provides
    RedeemSignatureDisplayRouter provideRedeemSignatureDisplayRouter() {
        return new RedeemSignatureDisplayRouter();
    }

    @Provides
    GenericWalletInteract provideGenericWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }
}
