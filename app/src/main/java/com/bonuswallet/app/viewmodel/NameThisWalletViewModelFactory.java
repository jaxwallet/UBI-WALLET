package com.bonuswallet.app.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.interact.GenericWalletInteract;

public class NameThisWalletViewModelFactory implements ViewModelProvider.Factory {
    private final GenericWalletInteract genericWalletInteract;
    private final Context context;

    public NameThisWalletViewModelFactory(GenericWalletInteract genericWalletInteract, Context context) {
        this.genericWalletInteract = genericWalletInteract;
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NameThisWalletViewModel(
                genericWalletInteract, context);
    }
}
