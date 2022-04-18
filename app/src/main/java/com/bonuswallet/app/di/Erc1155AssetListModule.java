package com.bonuswallet.app.di;

import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.Erc1155AssetListViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc1155AssetListModule {
    @Provides
    Erc1155AssetListViewModelFactory provideErc1155AssetListViewModelFactory(
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService)
    {
        return new Erc1155AssetListViewModelFactory(assetDefinitionService, tokensService);
    }
}
