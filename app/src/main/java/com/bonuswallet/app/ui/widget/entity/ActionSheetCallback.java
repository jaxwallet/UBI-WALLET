package com.bonuswallet.app.ui.widget.entity;

import com.bonuswallet.app.entity.SignAuthenticationCallback;
import com.bonuswallet.app.web3.entity.Web3Transaction;

/**
 * Created by JB on 27/11/2020.
 */
public interface ActionSheetCallback
{
    void getAuthorisation(SignAuthenticationCallback callback);
    void sendTransaction(Web3Transaction tx);
    void dismissed(String txHash, long callbackId, boolean actionCompleted);
    void notifyConfirm(String mode);
    default void signTransaction(Web3Transaction tx) { }; // only WalletConnect uses this so far
}
