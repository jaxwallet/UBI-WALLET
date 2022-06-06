package com.bonuswallet.app.repository;

import android.util.Log;

import com.bonuswallet.app.BuildConfig;
import com.bonuswallet.app.entity.CustomViewSettings;
import com.bonuswallet.app.entity.Wallet;
import com.bonuswallet.app.service.AccountKeystoreService;
import com.bonuswallet.app.service.KeyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.realm.Realm;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WalletRepository implements WalletRepositoryType
{
	private static final String TAG = "WalletRepository";
	private final PreferenceRepositoryType preferenceRepositoryType;
	private final AccountKeystoreService accountKeystoreService;
	private final EthereumNetworkRepositoryType networkRepository;
	private final WalletDataRealmSource walletDataRealmSource;
	private final KeyService keyService;

	public WalletRepository(PreferenceRepositoryType preferenceRepositoryType, AccountKeystoreService accountKeystoreService, EthereumNetworkRepositoryType networkRepository, WalletDataRealmSource walletDataRealmSource, KeyService keyService)
	{
		this.preferenceRepositoryType = preferenceRepositoryType;
		this.accountKeystoreService = accountKeystoreService;
		this.networkRepository = networkRepository;
		this.walletDataRealmSource = walletDataRealmSource;
		this.keyService = keyService;
	}

	@Override
	public Single<Wallet[]> fetchWallets()
	{
		return accountKeystoreService.fetchAccounts()
				.flatMap(wallets -> walletDataRealmSource.populateWalletData(wallets, keyService))
				.map(wallets -> {
					if (preferenceRepositoryType.getCurrentWalletAddress() == null && wallets.length > 0)
					{
						preferenceRepositoryType.setCurrentWalletAddress(wallets[0].address);
					}
					return wallets;
				});
	}

	@Override
	public Single<Wallet> findWallet(String address)
	{
		return fetchWallets()
				.flatMap(wallets -> {
					if (wallets.length == 0) return Single.error(new NoWallets("No wallets"));
					Wallet firstWallet = null;
					for (Wallet wallet : wallets)
					{
						if (address == null || wallet.sameAddress(address))
						{
							return Single.just(wallet);
						}
						if (firstWallet == null) firstWallet = wallet;
					}

					return Single.just(firstWallet);
				});
	}

	@Override
	public Single<Wallet> createWallet(String password)
	{
		return accountKeystoreService.createAccount(password);
	}

	@Override
	public Single<Wallet> importKeystoreToWallet(String store, String password, String newPassword)
	{
		return accountKeystoreService.importKeystore(store, password, newPassword);
	}

	@Override
	public Single<Wallet> importPrivateKeyToWallet(String privateKey, String newPassword)
	{
		return accountKeystoreService.importPrivateKey(privateKey, newPassword);
	}

	@Override
	public Single<String> exportWallet(Wallet wallet, String password, String newPassword)
	{
		return accountKeystoreService.exportAccount(wallet, password, newPassword);
	}

	@Override
	public Completable deleteWallet(String address, String password)
	{
		return accountKeystoreService.deleteAccount(address, password);
	}

	@Override
	public Single<Wallet> deleteWalletFromRealm(Wallet wallet)
	{
		return walletDataRealmSource.deleteWallet(wallet);
	}

	@Override
	public Completable setDefaultWallet(Wallet wallet)
	{
		return Completable.fromAction(() -> preferenceRepositoryType.setCurrentWalletAddress(wallet.address));
	}

	@Override
	public void updateBackupTime(String walletAddr)
	{
		walletDataRealmSource.updateBackupTime(walletAddr);
	}

	@Override
	public void updateWarningTime(String walletAddr)
	{
		walletDataRealmSource.updateWarningTime(walletAddr);
	}

	@Override
	public Single<Boolean> getWalletBackupWarning(String walletAddr)
	{
		return walletDataRealmSource.getWalletBackupWarning(walletAddr);
	}

	@Override
	public Single<String> getWalletRequiresBackup(String walletAddr)
	{
		return walletDataRealmSource.getWalletRequiresBackup(walletAddr);
	}

	@Override
	public void setIsDismissed(String walletAddr, boolean isDismissed)
	{
		walletDataRealmSource.setIsDismissed(walletAddr, isDismissed);
	}

	@Override
	public Single<Wallet> getDefaultWallet()
	{
		return Single.fromCallable(preferenceRepositoryType::getCurrentWalletAddress)
				.flatMap(this::findWallet);
	}

	@Override
	public Single<Wallet[]> storeWallets(Wallet[] wallets)
	{
		return walletDataRealmSource.storeWallets(wallets);
	}

	@Override
	public Single<Wallet> storeWallet(Wallet wallet)
	{
		return walletDataRealmSource.storeWallet(wallet);
	}

	@Override
	public void updateWalletData(Wallet wallet, Realm.Transaction.OnSuccess onSuccess)
	{
		walletDataRealmSource.updateWalletData(wallet, onSuccess);
	}

	@Override
	public Single<String> getName(String address)
	{
		return walletDataRealmSource.getName(address);
	}

	@Override
    public boolean keystoreExists(String address)
    {
		Wallet[] wallets = fetchWallets().blockingGet();
		for (Wallet w : wallets)
		{
			if (w.sameAddress(address)) return true;
		}
		return false;
    }

    @Override
	public Realm getWalletRealm()
	{
		return walletDataRealmSource.getWalletRealm();
	}

	@Override
	public Single<String> getKYCStatus(String walletAddr)
	{
		if(BuildConfig.DEBUG)
			Log.d(TAG, "getkycaddress: " + walletAddr);
		return Single.fromCallable(() -> {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
					.url(CustomViewSettings.kycEndpoint + walletAddr)
					.build();
			Response response = client.newCall(request).execute();
			String responseStr = response.body().string();
			if(BuildConfig.DEBUG)
						Log.d(TAG, "getkycstats: " + responseStr);
			JSONObject object = new JSONObject(responseStr);
			return object.getString("status");

		});
	}
}