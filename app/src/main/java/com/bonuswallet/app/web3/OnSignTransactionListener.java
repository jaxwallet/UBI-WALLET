package com.bonuswallet.app.web3;

import com.bonuswallet.app.web3.entity.Web3Transaction;

public interface OnSignTransactionListener {
    void onSignTransaction(Web3Transaction transaction, String url);
}
