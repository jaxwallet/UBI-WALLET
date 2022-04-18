package com.bonuswallet.app.interact;

import com.bonuswallet.app.repository.TokenRepositoryType;
import com.bonuswallet.app.entity.tokens.Token;
import com.bonuswallet.app.entity.Wallet;

import io.reactivex.Completable;

public class ChangeTokenEnableInteract {
    private final TokenRepositoryType tokenRepository;

    public ChangeTokenEnableInteract(TokenRepositoryType tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Completable setEnable(Wallet wallet, Token token, boolean enabled) {
        tokenRepository.setEnable(wallet, token, enabled);
        tokenRepository.setVisibilityChanged(wallet, token);
        return Completable.fromAction(() -> { });
    }
}
