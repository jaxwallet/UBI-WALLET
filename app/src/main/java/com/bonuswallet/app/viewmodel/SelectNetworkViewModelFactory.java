package com.bonuswallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.service.TokensService;

import io.reactivex.annotations.NonNull;

public class SelectNetworkViewModelFactory implements ViewModelProvider.Factory {

    private final EthereumNetworkRepositoryType networkRepository;
    private final TokensService tokensService;
    private final PreferenceRepositoryType preferenceRepository;

    public SelectNetworkViewModelFactory(EthereumNetworkRepositoryType networkRepository,
                                         TokensService tokensService,
                                         PreferenceRepositoryType preferenceRepository) {
        this.networkRepository = networkRepository;
        this.tokensService = tokensService;
        this.preferenceRepository = preferenceRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SelectNetworkViewModel(networkRepository, tokensService, preferenceRepository);
    }
}
