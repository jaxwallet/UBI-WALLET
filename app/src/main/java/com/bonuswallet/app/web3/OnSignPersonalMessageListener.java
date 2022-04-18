package com.bonuswallet.app.web3;

import com.bonuswallet.token.entity.EthereumMessage;

public interface OnSignPersonalMessageListener {
    void onSignPersonalMessage(EthereumMessage message);
}
