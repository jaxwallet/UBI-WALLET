package com.bonuswallet.app.di;

import com.bonuswallet.app.interact.CreateTransactionInteract;
import com.bonuswallet.app.interact.FetchTokensInteract;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.interact.MemPoolInteract;
import com.bonuswallet.app.interact.SignatureGenerateInteract;
import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.repository.TransactionRepositoryType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.KeyService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.viewmodel.RedeemSignatureDisplayModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by James on 25/01/2018.
 */

@Module
public class RedeemSignatureDisplayModule {
    @Provides
    RedeemSignatureDisplayModelFactory signatureDisplayModelFactory(
            GenericWalletInteract genericWalletInteract,
            SignatureGenerateInteract signatureGenerateInteract,
            CreateTransactionInteract createTransactionInteract,
            KeyService keyService,
            FetchTokensInteract fetchTokensInteract,
            MemPoolInteract memPoolInteract,
            TokensService tokensService,
            AssetDefinitionService assetDefinitionService) {
        return new RedeemSignatureDisplayModelFactory(
                genericWalletInteract, signatureGenerateInteract, createTransactionInteract, keyService, fetchTokensInteract, memPoolInteract, tokensService, assetDefinitionService);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    FetchTokensInteract provideFetchTokensInteract(TokenRepositoryType tokenRepository) {
        return new FetchTokensInteract(tokenRepository);
    }

    @Provides
    SignatureGenerateInteract provideSignatureGenerateInteract(WalletRepositoryType walletRepository) {
        return new SignatureGenerateInteract(walletRepository);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }

    @Provides
    MemPoolInteract provideMemPoolInteract(TokenRepositoryType tokenRepository) {
        return new MemPoolInteract(tokenRepository);
    }
}
