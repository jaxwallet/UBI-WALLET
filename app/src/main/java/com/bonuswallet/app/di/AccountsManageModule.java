package com.bonuswallet.app.di;

import android.content.Context;

import com.bonuswallet.app.interact.FetchWalletsInteract;
import com.bonuswallet.app.interact.FindDefaultNetworkInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.interact.SetDefaultWalletInteract;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.router.HomeRouter;
import com.bonuswallet.app.router.ImportWalletRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.TickerService;
import com.bonuswallet.app.viewmodel.WalletsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class AccountsManageModule {

	@Provides
	WalletsViewModelFactory provideAccountsManageViewModelFactory(
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
			AssetDefinitionService assetDefinitionService,
			Context context)
	{
		return new WalletsViewModelFactory(setDefaultWalletInteract,
				fetchWalletsInteract,
				genericWalletInteract,
				importWalletRouter,
				homeRouter,
				findDefaultNetworkInteract,
				keyService,
				ethereumNetworkRepository,
				tokenRepository,
				tickerService,
				assetDefinitionService,
				context);
	}

	@Provides
	SetDefaultWalletInteract provideSetDefaultAccountInteract(WalletRepositoryType accountRepository) {
		return new SetDefaultWalletInteract(accountRepository);
	}

	@Provides
	FetchWalletsInteract provideFetchAccountsInteract(WalletRepositoryType accountRepository) {
		return new FetchWalletsInteract(accountRepository);
	}

	@Provides
	GenericWalletInteract provideFindDefaultAccountInteract(WalletRepositoryType accountRepository) {
		return new GenericWalletInteract(accountRepository);
	}

	@Provides
	ImportWalletRouter provideImportAccountRouter() {
		return new ImportWalletRouter();
	}

	@Provides
	HomeRouter provideHomeRouter() {
		return new HomeRouter();
	}

	@Provides
	FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
			EthereumNetworkRepositoryType networkRepository) {
		return new FindDefaultNetworkInteract(networkRepository);
	}
}
