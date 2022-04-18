package com.bonuswallet.app.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.bonuswallet.app.entity.ActivityMeta;
import com.bonuswallet.app.entity.Wallet;
import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;

import io.realm.Realm;

public class TokenActivityViewModel extends BaseViewModel {
    private final MutableLiveData<ActivityMeta[]> transactions = new MutableLiveData<>();
    private final MutableLiveData<Boolean> newScriptFound = new MutableLiveData<>();
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final FetchTransactionsInteract fetchTransactionsInteract;

    public TokenActivityViewModel(AssetDefinitionService assetDefinitionService,
                                  FetchTransactionsInteract fetchTransactionsInteract,
                                  TokensService tokensService)
    {
        this.assetDefinitionService = assetDefinitionService;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.tokensService = tokensService;
    }

    public TokensService getTokensService()
    {
        return tokensService;
    }

    public FetchTransactionsInteract getTransactionsInteract()
    {
        return fetchTransactionsInteract;
    }

    public AssetDefinitionService getAssetDefinitionService()
    {
        return this.assetDefinitionService;
    }

    public Realm getRealmInstance(Wallet wallet)
    {
        return tokensService.getRealmInstance(wallet);
    }
}
