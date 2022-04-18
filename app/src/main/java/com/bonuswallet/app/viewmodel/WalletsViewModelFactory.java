package com.bonuswallet.app.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.bonuswallet.app.interact.FetchWalletsInteract;
import com.bonuswallet.app.interact.FindDefaultNetworkInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.interact.SetDefaultWalletInteract;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.router.HomeRouter;
import com.bonuswallet.app.router.ImportWalletRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.TickerService;

import javax.inject.Inject;

public class WalletsViewModelFactory implements ViewModelProvider.Factory {
    private final SetDefaultWalletInteract setDefaultWalletInteract;
    private final FetchWalletsInteract fetchWalletsInteract;
    private final GenericWalletInteract genericWalletInteract;
    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final ImportWalletRouter importWalletRouter;
    private final HomeRouter homeRouter;
    private final KeyService keyService;
    private final AssetDefinitionService assetService;
    private final Context context;
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;
    private final TokenRepositoryType tokenRepository;
    private final TickerService tickerService;

    @Inject
    public WalletsViewModelFactory(
            SetDefaultWalletInteract setDefaultWalletInteract,
            FetchWalletsInteract fetchWalletsInteract,
            GenericWalletInteract genericWalletInteract,
            ImportWalletRouter importWalletRouter,
            HomeRouter homeRouter,
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            KeyService keyService,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokenRepositoryType tokenRepository,
            TickerService tickerService,
            AssetDefinitionService assetService,
            Context context) {
        this.setDefaultWalletInteract = setDefaultWalletInteract;
        this.fetchWalletsInteract = fetchWalletsInteract;
        this.genericWalletInteract = genericWalletInteract;
        this.importWalletRouter = importWalletRouter;
        this.homeRouter = homeRouter;
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.keyService = keyService;
        this.ethereumNetworkRepository = ethereumNetworkRepository;
        this.tokenRepository = tokenRepository;
        this.tickerService = tickerService;
        this.assetService = assetService;
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WalletsViewModel(
                setDefaultWalletInteract,
                fetchWalletsInteract,
                genericWalletInteract,
                importWalletRouter,
                homeRouter,
                findDefaultNetworkInteract,
                keyService,
                ethereumNetworkRepository,
                tokenRepository,
                tickerService,
                assetService,
                context);
    }
}
