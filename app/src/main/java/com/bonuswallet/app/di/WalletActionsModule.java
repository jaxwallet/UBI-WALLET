package com.bonuswallet.app.di;

import dagger.Module;
import dagger.Provides;
import com.bonuswallet.app.interact.DeleteWalletInteract;
import com.bonuswallet.app.interact.ExportWalletInteract;
import com.bonuswallet.app.interact.FetchWalletsInteract;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.router.HomeRouter;
import com.bonuswallet.app.viewmodel.WalletActionsViewModelFactory;

@Module
class WalletActionsModule {
	@Provides
	WalletActionsViewModelFactory provideWalletActionsViewModelFactory(
			HomeRouter homeRouter,
			DeleteWalletInteract deleteWalletInteract,
			ExportWalletInteract exportWalletInteract,
			FetchWalletsInteract fetchWalletsInteract) {
		return new WalletActionsViewModelFactory(
				homeRouter,
				deleteWalletInteract,
				exportWalletInteract,
				fetchWalletsInteract);
	}

	@Provides
	HomeRouter provideHomeRouter() {
		return new HomeRouter();
	}

	@Provides
	DeleteWalletInteract provideDeleteAccountInteract(
			WalletRepositoryType accountRepository) {
		return new DeleteWalletInteract(accountRepository);
	}

	@Provides
	ExportWalletInteract provideExportWalletInteract(
			WalletRepositoryType walletRepository) {
		return new ExportWalletInteract(walletRepository);
	}

	@Provides
    FetchWalletsInteract provideFetchAccountsInteract(WalletRepositoryType accountRepository) {
		return new FetchWalletsInteract(accountRepository);
	}
}
