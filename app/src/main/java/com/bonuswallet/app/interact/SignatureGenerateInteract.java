package com.bonuswallet.app.interact;

import android.util.Log;

import com.bonuswallet.app.entity.ContractType;
import com.bonuswallet.app.repository.WalletRepositoryType;
import com.bonuswallet.app.entity.MessagePair;
import com.bonuswallet.app.entity.SignaturePair;

import java.math.BigInteger;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by James on 25/01/2018.
 */

public
class SignatureGenerateInteract {

    private final WalletRepositoryType walletRepository;

    public SignatureGenerateInteract(WalletRepositoryType walletRepository) {
        this.walletRepository = walletRepository;
    }

    //TODO: Sign message here not in the additional field
    public Single<MessagePair> getMessage(List<BigInteger> tickets, String contract, ContractType contractType) {
        return Single.fromCallable(() -> {
            String selectionStr = "";
            long divisor;
            if (contractType.equals(ContractType.ERC721_TICKET))
            {
                selectionStr = SignaturePair.generateSelection721Tickets(tickets);
                //use 10 minute intervals
                divisor = (10 * 60 * 1000);
            }
            else
            {
                selectionStr = SignaturePair.generateSelection(tickets);
                //use 30 second intervals
                divisor = (30 * 1000);
            }
            long currentTime = System.currentTimeMillis();

            long minsT = currentTime / divisor;
            int minsTime = (int) minsT;
            //This is the plain text message that gets signed
            String plainMessage = selectionStr + "," + minsTime + "," + contract.toLowerCase();
            Log.d("SIG", plainMessage);
            return new MessagePair(selectionStr, plainMessage);
        });
    }
}
