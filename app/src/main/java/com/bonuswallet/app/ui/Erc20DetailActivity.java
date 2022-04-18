package com.bonuswallet.app.ui;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bonuswallet.app.BuildConfig;
import com.bonuswallet.app.C;
import com.bonuswallet.app.R;
import com.bonuswallet.app.entity.AddressMode;
import com.bonuswallet.app.entity.BuyCryptoInterface;
import com.bonuswallet.app.entity.StandardFunctionInterface;
import com.bonuswallet.app.entity.Wallet;
import com.bonuswallet.app.entity.WalletType;
import com.bonuswallet.app.entity.tokens.Token;
import com.bonuswallet.app.entity.tokens.TokenCardMeta;
import com.bonuswallet.app.repository.EthereumNetworkBase;
import com.bonuswallet.app.repository.entity.RealmToken;
import com.bonuswallet.app.ui.widget.adapter.ActivityAdapter;
import com.bonuswallet.app.ui.widget.adapter.TokensAdapter;
import com.bonuswallet.app.viewmodel.Erc20DetailViewModel;
import com.bonuswallet.app.viewmodel.Erc20DetailViewModelFactory;
import com.bonuswallet.app.widget.ActivityHistoryList;
import com.bonuswallet.app.widget.CertifiedToolbarView;
import com.bonuswallet.app.widget.FunctionButtonBar;
//import com.alphawallet.ethereum.EthereumNetworkBase;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.bonuswallet.app.C.ETH_SYMBOL;
import static com.bonuswallet.app.C.Key.WALLET;
import static com.bonuswallet.app.repository.TokensRealmSource.databaseKey;
import static com.bonuswallet.app.ui.MyAddressActivity.KEY_MODE;
import static com.bonuswallet.ethereum.EthereumNetworkBase.MAINNET_ID;

public class Erc20DetailActivity extends BaseActivity implements StandardFunctionInterface, BuyCryptoInterface
{
    @Inject
    Erc20DetailViewModelFactory erc20DetailViewModelFactory;
    Erc20DetailViewModel viewModel;

    public static final int HISTORY_LENGTH = 5;

    private String symbol;
    private Wallet wallet;
    private Token token;
    private TokenCardMeta tokenMeta;
    private RecyclerView tokenView;
    private CertifiedToolbarView toolbarView;

    private TokensAdapter tokenViewAdapter;
    private ActivityHistoryList activityHistoryList = null;
    private Realm realm = null;
    private RealmResults<RealmToken> realmTokenUpdates;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erc20_token_detail);
        symbol = null;
        toolbar();
        setTitle("");
        setupViewModel();

        if (savedInstanceState != null && savedInstanceState.containsKey(C.EXTRA_SYMBOL))
        {
            symbol = savedInstanceState.getString(C.EXTRA_ACTION_NAME);
            wallet = savedInstanceState.getParcelable(WALLET);
            long chainId = savedInstanceState.getLong(C.EXTRA_CHAIN_ID, MAINNET_ID);
            token = viewModel.getTokensService().getToken(chainId, savedInstanceState.getString(C.EXTRA_ADDRESS));
        }
        else
        {
            getIntentData();
        }

        setUpTokenView();
        setUpRecentTransactionsView();
    }

    private void setupViewModel()
    {
        toolbarView = findViewById(R.id.toolbar);

        if (viewModel == null)
        {
            viewModel = new ViewModelProvider(this, erc20DetailViewModelFactory)
                    .get(Erc20DetailViewModel.class);
            viewModel.sig().observe(this, sigData -> toolbarView.onSigData(sigData, this));
            viewModel.newScriptFound().observe(this, this::onNewScript);
            findViewById(R.id.certificate_spinner).setVisibility(View.VISIBLE);
        }
    }

    private void onNewScript(Boolean hasNewScript)
    {
        //found a new tokenscript for this token, create a new meta with balance set to trigger view update; view will update the token name
        tokenViewAdapter.updateDetailToken(new TokenCardMeta(token.tokenInfo.chainId, token.getAddress(), "force_update",
                token.updateBlancaTime, token.lastTxCheck, token.getInterfaceSpec()), true);
        viewModel.checkTokenScriptValidity(token); //check script signature
    }

    private void setUpRecentTransactionsView()
    {
        if (activityHistoryList != null) return;
        activityHistoryList = findViewById(R.id.history_list);
        ActivityAdapter adapter = new ActivityAdapter(viewModel.getTokensService(), viewModel.getTransactionsInteract(),
                viewModel.getAssetDefinitionService());

        adapter.setDefaultWallet(wallet);

        activityHistoryList.setupAdapter(adapter);
        activityHistoryList.startActivityListeners(viewModel.getRealmInstance(wallet), wallet,
                token, viewModel.getTokensService(), BigInteger.ZERO, HISTORY_LENGTH);
    }

    private void setUpTokenView()
    {
        if (tokenViewAdapter != null) return;
        tokenView = findViewById(R.id.token_view);
        tokenView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        tokenViewAdapter = new TokensAdapter(null, viewModel.getAssetDefinitionService(), viewModel.getTokensService(), null);
        tokenViewAdapter.updateDetailToken(tokenMeta, true);
        tokenView.setAdapter(tokenViewAdapter);
        setTokenListener();
        setupButtons();
        viewModel.checkTokenScriptValidity(token);
    }

    private void setupButtons()
    {
        if (BuildConfig.DEBUG || wallet.type != WalletType.WATCH)
        {
            FunctionButtonBar functionBar = findViewById(R.id.layoutButtons);
            functionBar.setupBuyFunction(this, viewModel.getOnRampRepository());
            functionBar.setupFunctions(this, viewModel.getAssetDefinitionService(), token, null, null);
            functionBar.revealButtons();
            functionBar.setWalletType(wallet.type);
        }
    }

    private void getIntentData()
    {
        if (symbol != null) return;

        symbol = getIntent().getStringExtra(C.EXTRA_SYMBOL);
        symbol = symbol == null ? ETH_SYMBOL : symbol;
        wallet = getIntent().getParcelableExtra(WALLET);
        long chainId = getIntent().getLongExtra(C.EXTRA_CHAIN_ID, MAINNET_ID);
        token = viewModel.getTokensService().getTokenOrBase(chainId, getIntent().getStringExtra(C.EXTRA_ADDRESS));
        tokenMeta = new TokenCardMeta(token);
        viewModel.checkForNewScript(token);
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString(C.EXTRA_SYMBOL, symbol);
        outState.putParcelable(WALLET, wallet);
        outState.putLong(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        outState.putString(C.EXTRA_ADDRESS, token.getAddress());
    }

    private void setTokenListener()
    {
        if (realm == null) realm = viewModel.getRealmInstance(wallet);
        if (realmTokenUpdates != null) realmTokenUpdates.removeAllChangeListeners();
        String dbKey = databaseKey(token.tokenInfo.chainId, token.tokenInfo.address.toLowerCase());
        realmTokenUpdates = realm.where(RealmToken.class).equalTo("address", dbKey)
                .greaterThan("addedTime", System.currentTimeMillis()- 5 * DateUtils.MINUTE_IN_MILLIS).findAllAsync();
        realmTokenUpdates.addChangeListener(realmTokens -> {
            if (realmTokens.size() == 0) return;
            for (RealmToken t : realmTokens)
            {
                TokenCardMeta meta = new TokenCardMeta(t.getChainId(), t.getTokenAddress(), t.getBalance(),
                        t.getUpdateTime(), t.getLastTxTime(), t.getContractType());
                meta.isEnabled = t.isEnabled();

                if (tokenMeta == null)
                {
                    tokenMeta = meta;
                }
                else if (!tokenMeta.balance.equals(meta.balance))
                {
                    playNotification();
                }

                tokenViewAdapter.updateDetailToken(meta, true);
            }
        });
    }

    private void playNotification()
    {
        try
        {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(this, notification);
            r.play();
        }
        catch (Exception e)
        {
            //empty
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_qr, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        else if (item.getItemId() == R.id.action_qr)
        {
            viewModel.showContractInfo(this, wallet, token);
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityHistoryList != null) activityHistoryList.onDestroy();
        if (realmTokenUpdates != null) realmTokenUpdates.removeAllChangeListeners();
        if (tokenViewAdapter != null && tokenView != null) tokenViewAdapter.onDestroy(tokenView);
        if (realm != null) realm.close();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        viewModel.getTokensService().clearFocusToken();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (viewModel == null)
        {
            restartView();
        }
        else
        {
            if (activityHistoryList != null)
            {
                //reset the transaction history
                activityHistoryList.resetAdapter();
                activityHistoryList.startActivityListeners(viewModel.getRealmInstance(wallet), wallet,
                        token, viewModel.getTokensService(), BigInteger.ZERO, HISTORY_LENGTH);
            }
            viewModel.getTokensService().setFocusToken(token);
            viewModel.restartServices();
        }
    }

    private void restartView()
    {
        setupViewModel();
        getIntentData();
        setUpTokenView();
        setUpRecentTransactionsView();
    }

    @Override
    public void handleTokenScriptFunction(String function, List<BigInteger> selection)
    {
        Intent intent = new Intent(this, FunctionActivity.class);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        intent.putExtra(C.EXTRA_ADDRESS, token.getAddress());
        intent.putExtra(WALLET, wallet);
        intent.putExtra(C.EXTRA_STATE, function);
        intent.putExtra(C.EXTRA_TOKEN_ID, BigInteger.ZERO.toString(16));
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }

    @Override
    public void showSend()
    {
        viewModel.showSendToken(this, wallet, token);
    }

    @Override
    public void showReceive()
    {
        viewModel.showMyAddress(this, wallet, token);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        String transactionHash = null;

        switch (requestCode)
        {
            case C.COMPLETED_TRANSACTION: //completed a transaction send and got with either a hash or a null
                if (data != null) transactionHash = data.getStringExtra(C.EXTRA_TXHASH);
                if (transactionHash != null)
                {
                    //display transaction complete message

                }
                break;
        }
    }

    @Override
    public void handleClick(String action, int actionId)
    {
        if (actionId == R.string.convert_to_xdai)
        {
            openDapp(C.XDAI_BRIDGE_DAPP);
        }
        else if (actionId == R.string.swap_with_quickswap)
        {
            String queryPath = "?use=v2&inputCurrency=" + (token.isEthereum() ? ETH_SYMBOL : token.getAddress());
            openDapp(C.QUICKSWAP_EXCHANGE_DAPP + queryPath);
        }
        else if (actionId == R.string.exchange_with_oneinch)
        {
            openDapp(formatOneInchCall(token));
        }
    }

    private String formatOneInchCall(Token token)
    {
        String token1;
        String token2;
        if (token.isERC20())
        {
            token1 = token.getAddress();
            token2 = EthereumNetworkBase.getNetworkInfo(token.tokenInfo.chainId).symbol;
        }
        else
        {
            token1 = token.tokenInfo.symbol;
            token2 = "";
        }

        return C.ONEINCH_EXCHANGE_DAPP.replace("[CHAIN]", String.valueOf(token.tokenInfo.chainId))
                .replace("[TOKEN1]", token1).replace("[TOKEN2]", token2);
    }

    private void openDapp(String dappURL)
    {
        //switch to dappbrowser and open at dappURL
        Intent intent = new Intent();
        intent.putExtra(C.DAPP_URL_LOAD, dappURL);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void handleBuyFunction(Token token)
    {
        Intent intent = viewModel.getBuyIntent(wallet.address, token);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void handleGeneratePaymentRequest(Token token) {
        Intent intent = new Intent(this, MyAddressActivity.class);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        intent.putExtra(C.EXTRA_ADDRESS, token.getAddress());
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        intent.putExtra(KEY_MODE, AddressMode.MODE_POS.ordinal());
        this.startActivity(intent);
    }
}
