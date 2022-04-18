package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.FetchWalletsInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.CurrencyRepository;
import com.bonuswallet.app.repository.CurrencyRepositoryType;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.LocaleRepository;
import com.bonuswallet.app.repository.LocaleRepositoryType;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.router.ExternalBrowserRouter;
import com.bonuswallet.app.router.ImportTokenRouter;
import com.bonuswallet.app.router.MyAddressRouter;
import com.bonuswallet.app.service.AnalyticsServiceType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TickerService;
import com.bonuswallet.app.service.TransactionsService;
import com.bonuswallet.app.viewmodel.HomeViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class HomeModule {
    @Provides
    HomeViewModelFactory provideHomeViewModelFactory(
            PreferenceRepositoryType preferenceRepository,
            LocaleRepositoryType localeRepository,
            ImportTokenRouter importTokenRouter,
            AssetDefinitionService assetDefinitionService,
            GenericWalletInteract genericWalletInteract,
            FetchWalletsInteract fetchWalletsInteract,
            CurrencyRepositoryType currencyRepository,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            MyAddressRouter myAddressRouter,
            TransactionsService transactionsService,
            TickerService tickerService,
            AnalyticsServiceType analyticsService,
            ExternalBrowserRouter externalBrowserRouter) {
        return new HomeViewModelFactory(
                preferenceRepository,
                localeRepository,
                importTokenRouter,
                assetDefinitionService,
                genericWalletInteract,
                fetchWalletsInteract,
                currencyRepository,
                ethereumNetworkRepository,
                myAddressRouter,
                transactionsService,
                tickerService,
                analyticsService,
                externalBrowserRouter);
    }

    @Provides
    LocaleRepositoryType provideLocaleRepository(PreferenceRepositoryType preferenceRepository) {
        return new LocaleRepository(preferenceRepository);
    }

    @Provides
    ImportTokenRouter providesImportTokenRouter() { return new ImportTokenRouter(); }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    FetchWalletsInteract provideFetchWalletInteract(WalletRepositoryType walletRepository) {
        return new FetchWalletsInteract(walletRepository);
    }

    @Provides
    CurrencyRepositoryType provideCurrencyRepository(PreferenceRepositoryType preferenceRepository) {
        return new CurrencyRepository(preferenceRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }

    @Provides
    ExternalBrowserRouter provideBrowserRouter() {
        return new ExternalBrowserRouter();
    }
}
