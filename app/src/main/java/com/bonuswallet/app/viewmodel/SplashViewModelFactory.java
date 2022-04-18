package com.bonuswallet.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.interact.FetchWalletsInteract;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.service.KeyService;

public class SplashViewModelFactory implements ViewModelProvider.Factory {

    private final FetchWalletsInteract fetchWalletsInteract;
    private final PreferenceRepositoryType preferenceRepository;
    private final KeyService keyService;

    public SplashViewModelFactory(FetchWalletsInteract fetchWalletsInteract,
                                  PreferenceRepositoryType preferenceRepository,
                                  KeyService keyService) {
        this.fetchWalletsInteract = fetchWalletsInteract;
        this.preferenceRepository = preferenceRepository;
        this.keyService = keyService;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SplashViewModel(
                fetchWalletsInteract,
                preferenceRepository,
                keyService);
    }
}
