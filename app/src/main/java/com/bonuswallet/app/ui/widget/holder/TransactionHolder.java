package com.bonuswallet.app.ui.widget.holder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bonuswallet.app.C;
import com.bonuswallet.app.R;
import com.bonuswallet.app.entity.Transaction;
import com.bonuswallet.app.entity.TransactionMeta;
import com.bonuswallet.app.entity.tokens.Token;
import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.ui.TokenActivity;
import com.bonuswallet.app.ui.widget.entity.StatusType;
import com.bonuswallet.app.util.Utils;
import com.bonuswallet.app.widget.TokenIcon;
import com.bonuswallet.app.repository.EthereumNetworkBase;
import com.bonuswallet.token.entity.ContractAddress;

public class TransactionHolder extends BinderViewHolder<TransactionMeta> implements View.OnClickListener
{
    public static final int VIEW_TYPE = 1003;

    public static final int TRANSACTION_BALANCE_PRECISION = 4;

    public static final String DEFAULT_ADDRESS_ADDITIONAL = "default_address";

    private final TokenIcon tokenIcon;
    private final TextView type;
    private final TextView address;
    private final TextView value;
    private final TextView supplemental;
    private final TokensService tokensService;
    private final LinearLayout transactionBackground;
    private final FetchTransactionsInteract transactionsInteract;
    private final AssetDefinitionService assetService;

    private Transaction transaction;
    private String defaultAddress;
    private boolean fromTokenView;

    public TransactionHolder(ViewGroup parent, TokensService service, FetchTransactionsInteract interact, AssetDefinitionService svs)
    {
        super(R.layout.item_transaction, parent);
        tokenIcon = findViewById(R.id.token_icon);
        address = findViewById(R.id.address);
        type = findViewById(R.id.type);
        value = findViewById(R.id.value);
        supplemental = findViewById(R.id.supplimental);
        transactionBackground = findViewById(R.id.layout_background);
        tokensService = service;
        transactionsInteract = interact;
        assetService = svs;
        itemView.setOnClickListener(this);
    }

    @Override
    public void bind(@Nullable TransactionMeta data, @NonNull Bundle addition)
    {
        defaultAddress = addition.getString(DEFAULT_ADDRESS_ADDITIONAL);
        supplemental.setText("");
        fromTokenView = false;

        //fetch data from database
        transaction = transactionsInteract.fetchCached(defaultAddress, data.hash);

        if (this.transaction == null) {
            return;
        }

        value.setVisibility(View.VISIBLE);

        setChainElement();

        Token token = getOperationToken();
        if (token == null) return;

        String operationName = token.getOperationName(transaction, getContext());

        String transactionOperation = token.getTransactionResultValue(transaction, TRANSACTION_BALANCE_PRECISION);
        boolean shouldShowToken = token.shouldShowSymbol(transaction);
        value.setText(transactionOperation);
        CharSequence typeValue = Utils.createFormattedValue(getContext(), operationName, shouldShowToken ? token : null);

        type.setText(typeValue);
        //set address or contract name
        setupTransactionDetail(token);

        //set colours and up/down arrow
        tokenIcon.bindData(token, assetService);
        tokenIcon.setStatusIcon(token.getTxStatus(transaction));

        String supplementalTxt = transaction.getSupplementalInfo(token.getWallet(), EthereumNetworkBase.getChainSymbol(token.tokenInfo.chainId));
        supplemental.setText(supplementalTxt);
        supplemental.setTextColor(getContext().getColor(transaction.getSupplementalColour(supplementalTxt)));

        setTransactionStatus(transaction.blockNumber, transaction.error, transaction.isPending());
    }

    private void setupTransactionDetail(Token token)
    {
        String detailStr = token.getTransactionDetail(getContext(), transaction, tokensService);
        address.setText(Utils.formatElipse(detailStr));
    }

    @Override
    public void setFromTokenView()
    {
        fromTokenView = true;
    }

    private void setChainElement()
    {
    }

    private Token getOperationToken()
    {
        String operationAddress = transaction.getOperationTokenAddress();
        Token operationToken = tokensService.getToken(transaction.chainId, operationAddress);

        if (operationToken == null)
        {
            operationToken = tokensService.getToken(transaction.chainId, defaultAddress);
            tokensService.addUnknownTokenToCheck(new ContractAddress(transaction.chainId, operationAddress));
        }

        return operationToken;
    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(getContext(), TokenActivity.class);
        intent.putExtra(C.EXTRA_TXHASH, transaction.hash);
        intent.putExtra(C.EXTRA_STATE, fromTokenView);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        getContext().startActivity(intent);
    }

    private void setFailed()
    {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) supplemental.getLayoutParams();
        layoutParams.setMarginStart(10);
        String failure = getString(R.string.failed) + " ☹";
        supplemental.setText(failure);
        supplemental.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
    }

    private void setTransactionStatus(String blockNumber, String error, boolean isPending)
    {
        if (error != null && error.equals("1"))
        {
            setFailed();
            tokenIcon.setStatusIcon(StatusType.FAILED);
        }

        //Handle displaying the transaction item as pending or completed
        if (blockNumber.equals("-1"))
        {
            setFailed();
            tokenIcon.setStatusIcon(StatusType.REJECTED);
            address.setText(R.string.tx_rejected);
        }
        else if (isPending)
        {
            tokenIcon.setStatusIcon(StatusType.PENDING);
            type.setText(R.string.pending_transaction);
            transactionBackground.setBackgroundResource(R.drawable.background_pending_transaction);
        }
        else if (transactionBackground != null)
        {
            transactionBackground.setBackgroundResource(R.color.white);
        }
    }
}
