package com.bonuswallet.app.entity;
import com.bonuswallet.app.entity.cryptokeys.KeyEncodingType;
import com.bonuswallet.app.service.KeyService;

public interface ImportWalletCallback
{
    void walletValidated(String address, KeyEncodingType type, KeyService.AuthenticationLevel level);
}
