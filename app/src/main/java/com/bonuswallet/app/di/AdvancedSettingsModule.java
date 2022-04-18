package com.bonuswallet.app.di;

import com.bonuswallet.app.repository.CurrencyRepository;
import com.bonuswallet.app.repository.CurrencyRepositoryType;
import com.bonuswallet.app.repository.LocaleRepository;
import com.bonuswallet.app.repository.LocaleRepositoryType;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TransactionsService;
import com.bonuswallet.app.viewmodel.AdvancedSettingsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class AdvancedSettingsModule {
    @Provides
    AdvancedSettingsViewModelFactory provideAdvancedSettingsViewModelFactory(
            LocaleRepositoryType localeRepository,
            CurrencyRepositoryType currencyRepository,
            AssetDefinitionService assetDefinitionService,
            PreferenceRepositoryType preferenceRepository,
            TransactionsService transactionsService
    ) {
        return new AdvancedSettingsViewModelFactory(
                localeRepository,
                currencyRepository,
                assetDefinitionService,
                preferenceRepository,
                transactionsService);
    }

    @Provides
    LocaleRepositoryType provideLocaleRepository(PreferenceRepositoryType preferenceRepository) {
        return new LocaleRepository(preferenceRepository);
    }

    @Provides
    CurrencyRepositoryType provideCurrencyRepository(PreferenceRepositoryType preferenceRepository) {
        return new CurrencyRepository(preferenceRepository);
    }
}
