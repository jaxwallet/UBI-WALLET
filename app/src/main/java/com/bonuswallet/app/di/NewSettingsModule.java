package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.router.ManageWalletsRouter;
import com.bonuswallet.app.router.MyAddressRouter;
import com.bonuswallet.app.viewmodel.NewSettingsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class NewSettingsModule {
    @Provides
    NewSettingsViewModelFactory provideNewSettingsViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            MyAddressRouter myAddressRouter,
            ManageWalletsRouter manageWalletsRouter,
            PreferenceRepositoryType preferenceRepository
    ) {
        return new NewSettingsViewModelFactory(
                genericWalletInteract,
                myAddressRouter,
                manageWalletsRouter,
                preferenceRepository);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
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
