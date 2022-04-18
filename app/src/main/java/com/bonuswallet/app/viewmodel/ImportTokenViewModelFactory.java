package com.bonuswallet.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonuswallet.app.interact.CreateTransactionInteract;
import com.bonuswallet.app.interact.FetchTokensInteract;
import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.service.AlphaWalletService;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.GasService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.TokensService;

/**
 * Created by James on 9/03/2018.
 */

public class ImportTokenViewModelFactory implements ViewModelProvider.Factory {

    private final GenericWalletInteract genericWalletInteract;
    private final CreateTransactionInteract createTransactionInteract;
    private final FetchTokensInteract fetchTokensInteract;
    private final TokensService tokensService;
    private final AlphaWalletService alphaWalletService;
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;
    private final AssetDefinitionService assetDefinitionService;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final GasService gasService;
    private final KeyService keyService;

    public ImportTokenViewModelFactory(GenericWalletInteract genericWalletInteract,
                                       CreateTransactionInteract createTransactionInteract,
                                       FetchTokensInteract fetchTokensInteract,
                                       TokensService tokensService,
                                       AlphaWalletService alphaWalletService,
                                       EthereumNetworkRepositoryType ethereumNetworkRepository,
                                       AssetDefinitionService assetDefinitionService,
                                       FetchTransactionsInteract fetchTransactionsInteract,
                                       GasService gasService,
                                       KeyService keyService) {
        this.genericWalletInteract = genericWalletInteract;
        this.createTransactionInteract = createTransactionInteract;
        this.fetchTokensInteract = fetchTokensInteract;
        this.tokensService = tokensService;
        this.alphaWalletService = alphaWalletService;
        this.ethereumNetworkRepository = ethereumNetworkRepository;
        this.assetDefinitionService = assetDefinitionService;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.gasService = gasService;
        this.keyService = keyService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ImportTokenViewModel(genericWalletInteract, createTransactionInteract, fetchTokensInteract, tokensService, alphaWalletService, ethereumNetworkRepository, assetDefinitionService, fetchTransactionsInteract, gasService, keyService);
    }
}

