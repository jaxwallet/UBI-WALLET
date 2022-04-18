package com.bonuswallet.app.repository;

import com.bonuswallet.app.entity.OnRampContract;
import com.bonuswallet.app.entity.tokens.Token;

public interface OnRampRepositoryType {
    String getUri(String address, Token token);

    OnRampContract getContract(Token token);
}
