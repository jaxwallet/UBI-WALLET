package com.bonuswallet.app.router;

import android.app.Activity;
import android.content.Intent;

import com.bonuswallet.app.C;
import com.bonuswallet.app.entity.Wallet;
import com.bonuswallet.app.ui.AssetDisplayActivity;
import com.bonuswallet.app.entity.tokens.Token;

/**
 * Created by James on 22/01/2018.
 */

public class AssetDisplayRouter {

    public void open(Activity activity, Token token, Wallet wallet) {
        Intent intent = new Intent(activity, AssetDisplayActivity.class);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        intent.putExtra(C.EXTRA_ADDRESS, token.getAddress());
        intent.putExtra(C.Key.WALLET, wallet);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        activity.startActivityForResult(intent, C.TERMINATE_ACTIVITY);
    }
}
