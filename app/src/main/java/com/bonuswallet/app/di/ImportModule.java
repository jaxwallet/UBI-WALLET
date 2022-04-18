package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.ImportWalletInteract;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.AnalyticsServiceType;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.viewmodel.ImportWalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class ImportModule {
    @Provides
    ImportWalletViewModelFactory provideImportWalletViewModelFactory(
            ImportWalletInteract importWalletInteract,
            KeyService keyService,
            AnalyticsServiceType analyticsService) {
        return new ImportWalletViewModelFactory(importWalletInteract, keyService, analyticsService);
    }

    @Provides
    ImportWalletInteract provideImportWalletInteract(
            WalletRepositoryType walletRepository) {
        return new ImportWalletInteract(walletRepository);
    }
}
