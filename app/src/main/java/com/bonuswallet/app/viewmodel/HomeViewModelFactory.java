package com.bonuswallet.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.interact.FetchWalletsInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.CurrencyRepositoryType;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.LocaleRepositoryType;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.router.ExternalBrowserRouter;
import com.bonuswallet.app.router.ImportTokenRouter;
import com.bonuswallet.app.router.MyAddressRouter;
import com.bonuswallet.app.service.AnalyticsServiceType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TickerService;
import com.bonuswallet.app.service.TransactionsService;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private final PreferenceRepositoryType preferenceRepository;
    private final ImportTokenRouter importTokenRouter;
    private final LocaleRepositoryType localeRepository;
    private final AssetDefinitionService assetDefinitionService;
    private final GenericWalletInteract genericWalletInteract;
    private final FetchWalletsInteract fetchWalletsInteract;
    private final CurrencyRepositoryType currencyRepository;
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;
    private final MyAddressRouter myAddressRouter;
    private final TransactionsService transactionsService;
    private final TickerService tickerService;
    private final AnalyticsServiceType analyticsService;
    private final ExternalBrowserRouter externalBrowserRouter;

    public HomeViewModelFactory(
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
        this.preferenceRepository = preferenceRepository;
        this.localeRepository = localeRepository;
        this.importTokenRouter = importTokenRouter;
        this.assetDefinitionService = assetDefinitionService;
        this.genericWalletInteract = genericWalletInteract;
        this.fetchWalletsInteract = fetchWalletsInteract;
        this.currencyRepository = currencyRepository;
        this.ethereumNetworkRepository = ethereumNetworkRepository;
        this.myAddressRouter = myAddressRouter;
        this.transactionsService = transactionsService;
        this.tickerService = tickerService;
        this.analyticsService = analyticsService;
        this.externalBrowserRouter = externalBrowserRouter;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(
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
                externalBrowserRouter
        );
    }
}
