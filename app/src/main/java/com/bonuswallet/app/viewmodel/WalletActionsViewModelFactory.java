package com.bonuswallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import javax.inject.Inject;

import com.bonuswallet.app.interact.DeleteWalletInteract;
import com.bonuswallet.app.interact.ExportWalletInteract;
import com.bonuswallet.app.interact.FetchWalletsInteract;
import com.bonuswallet.app.router.HomeRouter;

public class WalletActionsViewModelFactory implements ViewModelProvider.Factory {
    private final HomeRouter homeRouter;
    private final DeleteWalletInteract deleteWalletInteract;
    private final ExportWalletInteract exportWalletInteract;
    private final FetchWalletsInteract fetchWalletsInteract;

    @Inject
    public WalletActionsViewModelFactory(
            HomeRouter homeRouter,
            DeleteWalletInteract deleteWalletInteract,
            ExportWalletInteract exportWalletInteract,
            FetchWalletsInteract fetchWalletsInteract) {
        this.homeRouter = homeRouter;
        this.deleteWalletInteract = deleteWalletInteract;
        this.exportWalletInteract = exportWalletInteract;
        this.fetchWalletsInteract = fetchWalletsInteract;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WalletActionsViewModel(
                homeRouter,
                deleteWalletInteract,
                exportWalletInteract,
                fetchWalletsInteract);
    }
}
