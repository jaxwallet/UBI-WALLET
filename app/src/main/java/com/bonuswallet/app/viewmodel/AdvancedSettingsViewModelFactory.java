package com.bonuswallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.bonuswallet.app.repository.CurrencyRepositoryType;
import com.bonuswallet.app.repository.LocaleRepositoryType;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TransactionsService;

public class AdvancedSettingsViewModelFactory implements ViewModelProvider.Factory {
    private final LocaleRepositoryType localeRepository;
    private final CurrencyRepositoryType currencyRepository;
    private final AssetDefinitionService assetDefinitionService;
    private final PreferenceRepositoryType preferenceRepository;
    private final TransactionsService transactionsService;

    public AdvancedSettingsViewModelFactory(
            LocaleRepositoryType localeRepository,
            CurrencyRepositoryType currencyRepository,
            AssetDefinitionService assetDefinitionService,
            PreferenceRepositoryType preferenceRepository,
            TransactionsService transactionsService) {
        this.localeRepository = localeRepository;
        this.currencyRepository = currencyRepository;
        this.assetDefinitionService = assetDefinitionService;
        this.preferenceRepository = preferenceRepository;
        this.transactionsService = transactionsService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AdvancedSettingsViewModel(
                localeRepository,
                currencyRepository,
                assetDefinitionService,
                preferenceRepository,
                transactionsService
        );
    }
}
