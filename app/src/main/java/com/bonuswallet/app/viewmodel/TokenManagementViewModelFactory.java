package com.bonuswallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.interact.ChangeTokenEnableInteract;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;

import io.reactivex.annotations.NonNull;

public class TokenManagementViewModelFactory implements ViewModelProvider.Factory {

    private final TokenRepositoryType tokenRepository;
    private final ChangeTokenEnableInteract changeTokenEnableInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;

    public TokenManagementViewModelFactory(TokenRepositoryType tokenRepository,
                                           ChangeTokenEnableInteract changeTokenEnableInteract,
                                           AssetDefinitionService assetDefinitionService,
                                           TokensService tokensService)
    {
        this.tokenRepository = tokenRepository;
        this.changeTokenEnableInteract = changeTokenEnableInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        return (T) new TokenManagementViewModel(
                tokenRepository,
                changeTokenEnableInteract,
                assetDefinitionService,
                tokensService
        );
    }
}
