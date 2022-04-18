package com.bonuswallet.app.ui.widget.holder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bonuswallet.app.C;
import com.bonuswallet.app.R;
import com.bonuswallet.app.entity.Transaction;
import com.bonuswallet.app.entity.tokens.Token;
import com.bonuswallet.app.interact.FetchTransactionsInteract;
import com.bonuswallet.app.repository.EventResult;
import com.bonuswallet.app.repository.entity.RealmAuxData;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;
import com.bonuswallet.app.ui.TokenActivity;
import com.bonuswallet.app.ui.widget.entity.TokenTransferData;
import com.bonuswallet.app.util.Utils;
import com.bonuswallet.app.widget.TokenIcon;
import com.bonuswallet.token.entity.EventDefinition;
import com.bonuswallet.token.entity.TSTokenView;
import com.bonuswallet.token.tools.TokenDefinition;

import java.math.BigInteger;
import java.util.Map;

import static com.bonuswallet.app.service.AssetDefinitionService.ASSET_SUMMARY_VIEW_NAME;
import static com.bonuswallet.app.ui.widget.holder.TransactionHolder.DEFAULT_ADDRESS_ADDITIONAL;
import static com.bonuswallet.ethereum.EthereumNetworkBase.MAINNET_ID;

/**
 * Created by JB on 17/12/2020.
 */
public class TransferHolder extends BinderViewHolder<TokenTransferData> implements View.OnClickListener
{
    public static final int VIEW_TYPE = 2017;

    private final TokenIcon tokenIcon;
    private final TextView type;
    private final TextView address;
    private final TextView value;
//    private final ChainName chainName;

    private final AssetDefinitionService assetDefinition;
    private Token token;
    private TokenTransferData transferData;

    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final TokensService tokensService;
    private String hashKey;
    private boolean fromTokenView;

    public TransferHolder(ViewGroup parent, TokensService service, FetchTransactionsInteract interact,
                          AssetDefinitionService svs)
    {
        super(R.layout.item_transaction, parent);
        tokenIcon = findViewById(R.id.token_icon);
        address = findViewById(R.id.address);
        type = findViewById(R.id.type);
        value = findViewById(R.id.value);
//        chainName = findViewById(R.id.chain_name);
        tokensService = service;
        itemView.setOnClickListener(this);
        assetDefinition = svs;

        fetchTransactionsInteract = interact;

        findViewById(R.id.layout_background).setBackgroundResource(R.color.white);
    }

    @Override
    public void bind(@Nullable TokenTransferData data, @NonNull Bundle addition)
    {
        fromTokenView = false;
        transferData = data;
        String walletAddress = addition.getString(DEFAULT_ADDRESS_ADDITIONAL);
        //pull event details from DB
        Transaction tx = fetchTransactionsInteract.fetchCached(walletAddress, data.hash);
        token = tokensService.getToken(data.chainId, data.tokenAddress);

        if (tx == null) { return; }

        if (token == null)
        {
            token = tokensService.getToken(data.chainId, walletAddress);
        }

        String sym = token != null ? token.getShortSymbol() : getContext().getString(R.string.eth);
        tokenIcon.bindData(token, assetDefinition);
        String itemView = null;

        if (data.getTimeStamp() % 1000 != 0)
        {
            findViewById(R.id.layout_background).setLabelFor(VIEW_TYPE);
        }
        else
        {
            findViewById(R.id.layout_background).setLabelFor(0);
        }

        TokenDefinition td = assetDefinition.getAssetDefinition(data.chainId, data.tokenAddress);
        if (td != null && td.getActivityCards().containsKey(data.eventName))
        {
            TSTokenView view = td.getActivityCards().get(data.eventName).getView(ASSET_SUMMARY_VIEW_NAME);
            if (view != null) itemView = view.tokenView;
        }

        String transactionValue = data.getEventAmount(token, tx);

        if (TextUtils.isEmpty(transactionValue))
        {
            value.setVisibility(View.GONE);
        }
        else
        {
            value.setText(getString(R.string.valueSymbol, transactionValue, sym));
        }

        CharSequence typeValue = Utils.createFormattedValue(getContext(), getTitle(data, tx), token);

        type.setText(typeValue);
        address.setText(data.getDetail(getContext(), tx, token, itemView));
        tokenIcon.setStatusIcon(data.getEventStatusType());

        //timestamp

        if (token.tokenInfo.chainId == MAINNET_ID)
        {
//            chainName.setVisibility(View.GONE);
        }
        else
        {
//            chainName.setVisibility(View.VISIBLE);
//            chainName.setChainID(token.tokenInfo.chainId);
        }

        hashKey = data.hash;
    }

    @Override
    public void setFromTokenView()
    {
        fromTokenView = true;
    }



    private String getTitle(TokenTransferData eventData, Transaction tx)
    {
        //TODO: pick up item-view
        int titleResource = eventData.getTitle(tx);
        if (titleResource == 0)
        {
            return eventData.eventName;
        }
        else
        {
            return getContext().getString(titleResource);
        }
    }

    private BigInteger getTokenId(TokenDefinition td, RealmAuxData eventData)
    {
        //pull tokenId
        if (token != null && token.isNonFungible() && td != null)
        {
            EventDefinition ev = td.getEventDefinition(eventData.getFunctionId());
            if (ev != null && ev.getFilterTopicValue().equals("tokenId"))
            {
                //filter topic is tokenId, therefore this event refers to a specific tokenId
                //isolate the tokenId
                Map<String, EventResult> resultMap = eventData.getEventResultMap();
                String filterIndexName = ev.getFilterTopicIndex();
                if (resultMap.containsKey(filterIndexName))
                {
                    return new BigInteger(resultMap.get(filterIndexName).value);
                }
            }
        }

        return BigInteger.ZERO;
    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(getContext(), TokenActivity.class);
        intent.putExtra(C.EXTRA_TXHASH, hashKey);
        intent.putExtra(C.EXTRA_STATE, fromTokenView);
        intent.putExtra(C.EXTRA_TOKEN_ID, transferData);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        getContext().startActivity(intent);
    }
}
