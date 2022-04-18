package com.bonuswallet.app.di;


import com.bonuswallet.app.interact.ChangeTokenEnableInteract;
import com.bonuswallet.app.interact.FetchTokensInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.router.AssetDisplayRouter;
import com.bonuswallet.app.router.ManageWalletsRouter;
import com.bonuswallet.app.router.TokenDetailRouter;
import com.bonuswallet.app.router.MyAddressRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.WalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class WalletModule {
    @Provides
    WalletViewModelFactory provideWalletViewModelFactory(
            FetchTokensInteract fetchTokensInteract,
            TokenDetailRouter tokenDetailRouter,
            AssetDisplayRouter assetDisplayRouter,
            GenericWalletInteract genericWalletInteract,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            ChangeTokenEnableInteract changeTokenEnableInteract,
            MyAddressRouter myAddressRouter,
            ManageWalletsRouter manageWalletsRouter,
            PreferenceRepositoryType preferenceRepository) {
        return new WalletViewModelFactory(
                fetchTokensInteract,
                tokenDetailRouter,
                assetDisplayRouter,
                genericWalletInteract,
                assetDefinitionService,
                tokensService,
                changeTokenEnableInteract,
                myAddressRouter,
                manageWalletsRouter,
                preferenceRepository);
    }

    @Provides
    FetchTokensInteract provideFetchTokensInteract(TokenRepositoryType tokenRepository) {
        return new FetchTokensInteract(tokenRepository);
    }

    @Provides
    TokenDetailRouter provideErc20DetailRouterRouter() {
        return new TokenDetailRouter();
    }

    @Provides
    AssetDisplayRouter provideAssetDisplayRouter() {
        return new AssetDisplayRouter();
    }

    @Provides
    GenericWalletInteract provideGenericWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    ChangeTokenEnableInteract provideChangeTokenEnableInteract(TokenRepositoryType tokenRepository) {
        return new ChangeTokenEnableInteract(tokenRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }

    @Provides
    ManageWalletsRouter provideManageWalletsRouter() {
        return new ManageWalletsRouter();
    }
}
