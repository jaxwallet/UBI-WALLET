package com.bonuswallet.app.web3.entity;

import com.bonuswallet.app.entity.DAppFunction;
import com.bonuswallet.token.entity.Signable;

/**
 * Created by James on 6/04/2019.
 * AJ TECHNOLOGIES LTD
 */
public interface FunctionCallback
{
    void signMessage(Signable sign, DAppFunction dAppFunction);
    void functionSuccess();
    void functionFailed();
}
