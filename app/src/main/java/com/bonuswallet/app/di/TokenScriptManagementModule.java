package com.bonuswallet.app.di;

import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.viewmodel.TokenScriptManagementViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TokenScriptManagementModule {
    @Provides
    TokenScriptManagementViewModelFactory tokenScriptManagementViewModelFactory(AssetDefinitionService assetDefinitionService)
    {
        return new TokenScriptManagementViewModelFactory(assetDefinitionService);
    }
}
