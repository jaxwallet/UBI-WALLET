package com.bonuswallet.app.entity;

import com.bonuswallet.app.entity.tokens.Token;

public interface BuyCryptoInterface {
    void handleBuyFunction(Token token);
    void handleGeneratePaymentRequest(Token token);
}
