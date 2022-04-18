package com.bonuswallet.app.di;

import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.GasSettingsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class GasSettingsModule {

    @Provides
    public GasSettingsViewModelFactory provideGasSettingsViewModelFactory(TokensService svs) {
        return new GasSettingsViewModelFactory(svs);
    }
}
