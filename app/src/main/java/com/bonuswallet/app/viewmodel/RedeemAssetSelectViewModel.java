package com.bonuswallet.app.viewmodel;

import android.content.Context;

import com.bonuswallet.app.entity.Wallet;
import com.bonuswallet.app.entity.tokens.Token;
import com.bonuswallet.app.interact.GenericWalletInteract;
import com.bonuswallet.app.router.RedeemSignatureDisplayRouter;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.ui.widget.entity.TicketRangeParcel;
import com.bonuswallet.token.entity.TicketRange;

/**
 * Created by James on 27/02/2018.
 */

public class RedeemAssetSelectViewModel extends BaseViewModel {
    private final RedeemSignatureDisplayRouter redeemSignatureDisplayRouter;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final GenericWalletInteract genericWalletInteract;

    public RedeemAssetSelectViewModel(
            RedeemSignatureDisplayRouter redeemSignatureDisplayRouter,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            GenericWalletInteract genericWalletInteract)
    {
        this.redeemSignatureDisplayRouter = redeemSignatureDisplayRouter;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
        this.genericWalletInteract = genericWalletInteract;
    }

    public void showRedeemSignature(Context ctx, TicketRange range, Token token)
    {
        genericWalletInteract.find()
                .subscribe(wallet -> onWallet(wallet, ctx, range, token), this::onError)
                .isDisposed();
    }

    private void onWallet(Wallet wallet, Context ctx, TicketRange range, Token token)
    {
        TicketRangeParcel parcel = new TicketRangeParcel(range);
        redeemSignatureDisplayRouter.open(ctx, wallet, token, parcel);
    }

    public TokensService getTokensService() { return tokensService; }

    public AssetDefinitionService getAssetDefinitionService()
    {
        return assetDefinitionService;
    }
}
