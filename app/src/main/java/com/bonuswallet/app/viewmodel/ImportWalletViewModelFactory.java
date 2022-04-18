package com.bonuswallet.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.interact.ImportWalletInteract;
import com.bonuswallet.app.service.AnalyticsServiceType;
import com.bonuswallet.app.service.KeyService;

public class ImportWalletViewModelFactory implements ViewModelProvider.Factory {

    private final ImportWalletInteract importWalletInteract;
    private final KeyService keyService;
    private final AnalyticsServiceType analyticsService;

    public ImportWalletViewModelFactory(ImportWalletInteract importWalletInteract, KeyService keyService,
                                        AnalyticsServiceType analyticsService) {
        this.importWalletInteract = importWalletInteract;
        this.keyService = keyService;
        this.analyticsService = analyticsService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ImportWalletViewModel(importWalletInteract, keyService, analyticsService);
    }
}
