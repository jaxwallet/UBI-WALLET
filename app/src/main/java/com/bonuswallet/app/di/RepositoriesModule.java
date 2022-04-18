package com.bonuswallet.app.di;

import android.content.Context;

import com.bonuswallet.app.repository.EthereumNetworkRepository;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.repository.OnRampRepository;
import com.bonuswallet.app.repository.OnRampRepositoryType;
import com.bonuswallet.app.repository.PreferenceRepositoryType;
import com.bonuswallet.app.repository.SharedPreferenceRepository;
import com.bonuswallet.app.repository.TokenLocalSource;
import com.bonuswallet.app.repository.TokenRepository;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.TokensRealmSource;
import com.bonuswallet.app.repository.TransactionLocalSource;
import com.bonuswallet.app.repository.TransactionRepository;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.repository.TransactionsRealmCache;
import com.bonuswallet.app.repository.WalletDataRealmSource;
import com.bonuswallet.app.repository.WalletRepository;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.AccountKeystoreService;
import com.bonuswallet.app.service.AlphaWalletService;
import com.bonuswallet.app.service.AnalyticsService;
import com.bonuswallet.app.service.AnalyticsServiceType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.GasService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.KeystoreAccountService;
import com.bonuswallet.app.service.NotificationService;
import com.bonuswallet.app.service.OpenSeaService;
import com.bonuswallet.app.service.RealmManager;
import com.bonuswallet.app.service.TickerService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.service.TransactionsNetworkClient;
import com.bonuswallet.app.service.TransactionsNetworkClientType;
import com.bonuswallet.app.service.TransactionsService;
import com.google.gson.Gson;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

import static com.bonuswallet.app.service.KeystoreAccountService.KEYSTORE_FOLDER;

@Module
public class RepositoriesModule {
	@Singleton
	@Provides
	PreferenceRepositoryType providePreferenceRepository(Context context) {
		return new SharedPreferenceRepository(context);
	}

	@Singleton
	@Provides
    AccountKeystoreService provideAccountKeyStoreService(Context context, KeyService keyService) {
        File file = new File(context.getFilesDir(), KEYSTORE_FOLDER);
		return new KeystoreAccountService(file, context.getFilesDir(), keyService);
	}

	@Singleton
    @Provides
	TickerService provideTickerService(OkHttpClient httpClient, PreferenceRepositoryType sharedPrefs, TokenLocalSource localSource) {
		return new TickerService(httpClient, sharedPrefs, localSource);
    }

	@Singleton
	@Provides
	EthereumNetworkRepositoryType provideEthereumNetworkRepository(
            PreferenceRepositoryType preferenceRepository,
			Context context) {
		return new EthereumNetworkRepository(preferenceRepository, context);
	}

	@Singleton
	@Provides
    WalletRepositoryType provideWalletRepository(
			PreferenceRepositoryType preferenceRepositoryType,
			AccountKeystoreService accountKeystoreService,
			EthereumNetworkRepositoryType networkRepository,
			WalletDataRealmSource walletDataRealmSource,
			KeyService keyService) {
		return new WalletRepository(
		        preferenceRepositoryType, accountKeystoreService, networkRepository, walletDataRealmSource, keyService);
	}

	@Singleton
	@Provides
	TransactionRepositoryType provideTransactionRepository(
			EthereumNetworkRepositoryType networkRepository,
			AccountKeystoreService accountKeystoreService,
            TransactionLocalSource inDiskCache,
			TransactionsService transactionsService) {
		return new TransactionRepository(
				networkRepository,
				accountKeystoreService,
				inDiskCache,
				transactionsService);
	}

	@Singleton
	@Provides
	OnRampRepositoryType provideOnRampRepository(Context context, AnalyticsServiceType analyticsServiceType) {
		return new OnRampRepository(context, analyticsServiceType);
	}

	@Singleton
    @Provides
    TransactionLocalSource provideTransactionInDiskCache(RealmManager realmManager) {
        return new TransactionsRealmCache(realmManager);
    }

	@Singleton
	@Provides
    TransactionsNetworkClientType provideBlockExplorerClient(
			OkHttpClient httpClient,
			Gson gson,
			RealmManager realmManager) {
		return new TransactionsNetworkClient(httpClient, gson, realmManager);
	}

	@Singleton
    @Provides
    TokenRepositoryType provideTokenRepository(
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokenLocalSource tokenLocalSource,
			OkHttpClient httpClient,
			Context context,
			TickerService tickerService) {
	    return new TokenRepository(
	            ethereumNetworkRepository,
				tokenLocalSource,
				httpClient,
				context,
				tickerService);
    }

    @Singleton
    @Provides
    TokenLocalSource provideRealmTokenSource(RealmManager realmManager, EthereumNetworkRepositoryType ethereumNetworkRepository) {
	    return new TokensRealmSource(realmManager, ethereumNetworkRepository);
    }

	@Singleton
	@Provides
	WalletDataRealmSource provideRealmWalletDataSource(RealmManager realmManager) {
		return new WalletDataRealmSource(realmManager);
	}

	@Singleton
	@Provides
	TokensService provideTokensService(EthereumNetworkRepositoryType ethereumNetworkRepository,
									   TokenRepositoryType tokenRepository,
									   TickerService tickerService,
									   OpenSeaService openseaService,
									   AnalyticsServiceType analyticsService) {
		return new TokensService(ethereumNetworkRepository, tokenRepository, tickerService, openseaService, analyticsService);
	}

	@Singleton
	@Provides
	TransactionsService provideTransactionsService(TokensService tokensService,
												   EthereumNetworkRepositoryType ethereumNetworkRepositoryType,
												   TransactionsNetworkClientType transactionsNetworkClientType,
												   TransactionLocalSource transactionLocalSource) {
		return new TransactionsService(tokensService, ethereumNetworkRepositoryType, transactionsNetworkClientType, transactionLocalSource);
	}

	@Singleton
	@Provides
    GasService provideGasService2(EthereumNetworkRepositoryType ethereumNetworkRepository, OkHttpClient client, RealmManager realmManager) {
		return new GasService(ethereumNetworkRepository, client, realmManager);
	}

	@Singleton
	@Provides
	OpenSeaService provideOpenseaService() {
		return new OpenSeaService();
	}

	@Singleton
	@Provides
    AlphaWalletService provideFeemasterService(OkHttpClient okHttpClient,
                                               TransactionRepositoryType transactionRepository,
                                               Gson gson) {
		return new AlphaWalletService(okHttpClient, transactionRepository, gson);
	}

	@Singleton
	@Provides
    NotificationService provideNotificationService(Context ctx) {
		return new NotificationService(ctx);
	}

	@Singleton
	@Provides
    AssetDefinitionService provideAssetDefinitionService(OkHttpClient okHttpClient, Context ctx, NotificationService notificationService, RealmManager realmManager,
														 TokensService tokensService, TokenLocalSource tls, TransactionRepositoryType trt,
														 AlphaWalletService alphaService) {
		return new AssetDefinitionService(okHttpClient, ctx, notificationService, realmManager, tokensService, tls, trt, alphaService);
	}

	@Singleton
	@Provides
	KeyService provideKeyService(Context ctx) {
		return new KeyService(ctx);
	}

	@Singleton
	@Provides
	AnalyticsServiceType provideAnalyticsService(Context ctx) {
		return new AnalyticsService(ctx);
	}
}
