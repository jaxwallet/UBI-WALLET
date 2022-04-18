package com.bonuswallet.app.di;

import dagger.Module;
import dagger.Provides;

import com.bonuswallet.app.interact.ExportWalletInteract;
import com.bonuswallet.app.interact.FetchWalletsInteract;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.viewmodel.BackupKeyViewModelFactory;

@Module
public class BackupKeyModule {
    @Provides
    BackupKeyViewModelFactory provideBackupKeyViewModelFactory(
            KeyService keyService,
            ExportWalletInteract exportWalletInteract,
            FetchWalletsInteract fetchWalletsInteract) {
        return new BackupKeyViewModelFactory(
                keyService,
                exportWalletInteract,
                fetchWalletsInteract);
    }

    @Provides
    ExportWalletInteract provideExportWalletInteract(
            WalletRepositoryType walletRepository) {
        return new ExportWalletInteract(walletRepository);
    }

    @Provides
    FetchWalletsInteract provideFetchAccountsInteract(WalletRepositoryType accountRepository) {
        return new FetchWalletsInteract(accountRepository);
    }
}