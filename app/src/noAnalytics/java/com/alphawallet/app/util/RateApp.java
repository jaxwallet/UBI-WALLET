package com.bonuswallet.app.util;

import android.app.Activity;
import com.bonuswallet.app.repository.PreferenceRepositoryType;

public class RateApp {
    // should be shown on 5th run or after the first transaction (afterTransaction == true)
    static public void showRateTheApp(Activity context, PreferenceRepositoryType preferenceRepository, boolean afterTransaction) {
        // empty implementation for noAnalytics flavor
    }
}
