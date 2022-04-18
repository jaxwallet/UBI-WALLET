package com.bonuswallet.app.viewmodel;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.router.ManageWalletsRouter;
import com.bonuswallet.app.router.MyAddressRouter;

public class NewSettingsViewModelFactory implements ViewModelProvider.Factory {
    private final MyAddressRouter myAddressRouter;
    private final GenericWalletInteract genericWalletInteract;
    private final ManageWalletsRouter manageWalletsRouter;
    private final PreferenceRepositoryType preferenceRepository;

    public NewSettingsViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            MyAddressRouter myAddressRouter,
            ManageWalletsRouter manageWalletsRouter,
            PreferenceRepositoryType preferenceRepository) {
        this.genericWalletInteract = genericWalletInteract;
        this.myAddressRouter = myAddressRouter;
        this.manageWalletsRouter = manageWalletsRouter;
        this.preferenceRepository = preferenceRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewSettingsViewModel(
                genericWalletInteract,
                myAddressRouter,
                manageWalletsRouter,
                preferenceRepository
        );
    }
}
