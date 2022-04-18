package com.bonuswallet.app.web3;


import com.bonuswallet.token.entity.EthereumTypedMessage;

public interface OnSignTypedMessageListener {
    void onSignTypedMessage(EthereumTypedMessage message);
}
