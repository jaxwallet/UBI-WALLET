package com.bonuswallet.app.repository;

import com.bonuswallet.app.entity.ActivityMeta;
import com.bonuswallet.app.entity.Transaction;
import com.bonuswallet.app.entity.Wallet;
import com.bonuswallet.app.repository.entity.RealmAuxData;

import org.web3j.protocol.core.methods.response.EthTransaction;

import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;

public interface TransactionLocalSource {
	Transaction fetchTransaction(Wallet wallet, String hash);
	void putTransaction(Wallet wallet, Transaction tx);
    void deleteTransaction(Wallet wallet, String oldTxHash);

	Realm getRealmInstance(Wallet wallet);

	Single<ActivityMeta[]> fetchActivityMetas(Wallet wallet, List<Long> networkFilters, long fetchTime, int fetchLimit);
	Single<ActivityMeta[]> fetchActivityMetas(Wallet wallet, long chainId, String tokenAddress, int historyCount);
	Single<ActivityMeta[]> fetchEventMetas(Wallet wallet, List<Long> networkFilters);

	void markTransactionBlock(String walletAddress, String hash, long blockValue);
	Transaction[] fetchPendingTransactions(String currentAddress);

	RealmAuxData fetchEvent(String walletAddress, String eventKey);

	Transaction storeRawTx(Wallet wallet, long chainId, EthTransaction object, long timeStamp, boolean isSuccessful);

    long fetchTxCompletionTime(Wallet wallet, String hash);

    Single<Boolean> deleteAllForWallet(String currentAddress);

    Single<Boolean> deleteAllTickers();
}
