package com.bonuswallet.app.viewmodel;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.bonuswallet.app.interact.ChangeTokenEnableInteract;
import com.bonuswallet.app.interact.FetchTokensInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.router.AssetDisplayRouter;
import com.bonuswallet.app.router.ManageWalletsRouter;
import com.bonuswallet.app.router.TokenDetailRouter;
import com.bonuswallet.app.router.MyAddressRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;

public class WalletViewModelFactory implements ViewModelProvider.Factory {
    private final FetchTokensInteract fetchTokensInteract;
    private final TokenDetailRouter tokenDetailRouter;
    private final AssetDisplayRouter assetDisplayRouter;
    private final GenericWalletInteract genericWalletInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final ChangeTokenEnableInteract changeTokenEnableInteract;
    private final MyAddressRouter myAddressRouter;
    private final ManageWalletsRouter manageWalletsRouter;
    private final PreferenceRepositoryType preferenceRepository;

    public WalletViewModelFactory(FetchTokensInteract fetchTokensInteract,
                                  TokenDetailRouter tokenDetailRouter,
                                  AssetDisplayRouter assetDisplayRouter,
                                  GenericWalletInteract genericWalletInteract,
                                  AssetDefinitionService assetDefinitionService,
                                  TokensService tokensService,
                                  ChangeTokenEnableInteract changeTokenEnableInteract,
                                  MyAddressRouter myAddressRouter,
                                  ManageWalletsRouter manageWalletsRouter,
                                  PreferenceRepositoryType preferenceRepository) {
        this.fetchTokensInteract = fetchTokensInteract;
        this.tokenDetailRouter = tokenDetailRouter;
        this.assetDisplayRouter = assetDisplayRouter;
        this.genericWalletInteract = genericWalletInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
        this.changeTokenEnableInteract = changeTokenEnableInteract;
        this.myAddressRouter = myAddressRouter;
        this.manageWalletsRouter = manageWalletsRouter;
        this.preferenceRepository = preferenceRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WalletViewModel(
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
}
