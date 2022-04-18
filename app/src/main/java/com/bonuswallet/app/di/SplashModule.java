package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.FetchWalletsInteract;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.viewmodel.SplashViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    @Provides
    SplashViewModelFactory provideSplashViewModelFactory(FetchWalletsInteract fetchWalletsInteract,
                                                         PreferenceRepositoryType preferenceRepository,
                                                         KeyService keyService) {
        return new SplashViewModelFactory(
                fetchWalletsInteract,
                preferenceRepository,
                keyService);
    }

    @Provides
    FetchWalletsInteract provideFetchWalletInteract(WalletRepositoryType walletRepository) {
        return new FetchWalletsInteract(walletRepository);
    }
}
