package com.bonuswallet.app.viewmodel;

import android.app.Activity;

import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.util.RateApp;

public class TransactionSuccessViewModel extends BaseViewModel {

    private final PreferenceRepositoryType preferenceRepository;

    public TransactionSuccessViewModel(
            PreferenceRepositoryType preferenceRepository
    ) {
        this.preferenceRepository = preferenceRepository;
    }

    public void tryToShowRateAppDialog(Activity context) {
        RateApp.showRateTheApp(context, preferenceRepository, true);
    }
}
