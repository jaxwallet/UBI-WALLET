package com.bonuswallet.app.di;

import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.viewmodel.TransactionSuccessViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TransactionSuccessModule {

    @Provides
    TransactionSuccessViewModelFactory provideTransactionSuccessViewModelFactory(PreferenceRepositoryType preferenceRepository) {
        return new TransactionSuccessViewModelFactory(
                preferenceRepository);
    }
}
