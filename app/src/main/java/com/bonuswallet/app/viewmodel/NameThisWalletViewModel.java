package com.bonuswallet.app.viewmodel;


import android.content.Context;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bonuswallet.app.entity.Wallet;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.TokenRepository;
import com.bonuswallet.app.util.AWEnsResolver;

import javax.annotation.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

import static com.bonuswallet.ethereum.EthereumNetworkBase.MAINNET_ID;

public class NameThisWalletViewModel extends BaseViewModel
{
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<String> ensName = new MutableLiveData<>();
    private final GenericWalletInteract genericWalletInteract;

    private final AWEnsResolver ensResolver;

    @Nullable
    Disposable ensResolveDisposable;

    NameThisWalletViewModel(GenericWalletInteract genericWalletInteract, Context context)
    {
        this.genericWalletInteract = genericWalletInteract;
        this.ensResolver = new AWEnsResolver(TokenRepository.getWeb3jService(MAINNET_ID), context);
    }


    @Override
    protected void onCleared()
    {
        super.onCleared();
        if (ensResolveDisposable != null && !ensResolveDisposable.isDisposed()) ensResolveDisposable.dispose();
    }

    public LiveData<Wallet> defaultWallet() { return defaultWallet; }
    public LiveData<String> ensName() { return ensName; }

    public void prepare() {
        disposable = genericWalletInteract
                .find()
                .subscribe(this::onDefaultWallet, this::onError);
    }

    private void onDefaultWallet(Wallet wallet) {
        defaultWallet.setValue(wallet);

        // skip resolve if wallet already has an ENSName
        if (!TextUtils.isEmpty(wallet.ENSname)) {
            onENSSuccess(wallet.ENSname);
            return ;
        }

        ensResolveDisposable = ensResolver.reverseResolveEns(wallet.address)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onENSSuccess);

    }

    private void onENSSuccess(String address) {
        ensName.setValue(address);
    }

    public void setWalletName(String name, Realm.Transaction.OnSuccess onSuccess)
    {
        genericWalletInteract.updateWalletInfo(defaultWallet.getValue(), name, onSuccess);
    }
}

