package com.bonuswallet.app.di;

import com.bonuswallet.app.viewmodel.HelpViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class HelpModule {
    @Provides
    HelpViewModelFactory provideMarketplaceViewModelFactory() {
        return new HelpViewModelFactory();
    }
}
