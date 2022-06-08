package com.bonuswallet.app.router;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.bonuswallet.app.C;
import com.bonuswallet.app.ui.TransferTicketDetailActivity;
import com.bonuswallet.app.entity.tokens.Token;
import com.bonuswallet.app.entity.Wallet;

/**
 * Created by James on 22/02/2018.
 */

public class TransferTicketDetailRouter {

    public void open(Context context, Token token, String ticketIDs, Wallet wallet) {
        Intent intent = new Intent(context, TransferTicketDetailActivity.class);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        intent.putExtra(C.EXTRA_ADDRESS, token.getAddress());
        intent.putExtra(C.EXTRA_TOKENID_LIST, ticketIDs);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setComponent(new ComponentName(context, "TransferTicketDetailRouter"));
        context.startActivity(intent);
    }

    public void openTransfer(Context context, Token token, String ticketIDs, Wallet wallet, int state) {
        Intent intent = new Intent(context, TransferTicketDetailActivity.class);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        intent.putExtra(C.EXTRA_ADDRESS, token.getAddress());
        intent.putExtra(C.EXTRA_TOKENID_LIST, ticketIDs);
        intent.putExtra(C.EXTRA_STATE, state);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        intent.setComponent(new ComponentName(context, "TransferTicketDetailRouter"));
        context.startActivity(intent);
    }
}
