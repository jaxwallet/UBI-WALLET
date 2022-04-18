package com.bonuswallet.app.entity;

import com.bonuswallet.token.entity.Signable;

public interface DAppFunction
{
    void DAppError(Throwable error, Signable message);
    void DAppReturn(byte[] data, Signable message);
}
