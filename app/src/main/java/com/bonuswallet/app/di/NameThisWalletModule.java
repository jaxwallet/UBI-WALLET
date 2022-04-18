package com.bonuswallet.app.di;

import android.content.Context;

import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.viewmodel.NameThisWalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class NameThisWalletModule {
    @Provides
    NameThisWalletViewModelFactory provideNameThisWalletViewModelFactory(
            GenericWalletInteract genericWalletInteract, Context context
    ) {
        return new NameThisWalletViewModelFactory(
                genericWalletInteract, context);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }
}
