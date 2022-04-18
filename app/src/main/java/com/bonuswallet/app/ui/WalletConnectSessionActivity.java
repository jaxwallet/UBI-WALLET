package com.bonuswallet.app.ui;

import static com.bonuswallet.app.C.Key.WALLET;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bonuswallet.app.C;
import com.bonuswallet.app.R;
import com.bonuswallet.app.entity.StandardFunctionInterface;
import com.bonuswallet.app.entity.Wallet;
import com.bonuswallet.app.entity.walletconnect.WalletConnectSessionItem;
import com.bonuswallet.app.ui.QRScanning.QRScanner;
import com.bonuswallet.app.viewmodel.WalletConnectViewModel;
import com.bonuswallet.app.viewmodel.WalletConnectViewModelFactory;
import com.bonuswallet.app.widget.ChainName;
import com.bumptech.glide.Glide;
import com.bonuswallet.app.widget.FunctionButtonBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by JB on 9/09/2020.
 */
public class WalletConnectSessionActivity extends BaseActivity implements StandardFunctionInterface
{
    @Inject
    WalletConnectViewModelFactory viewModelFactory;
    WalletConnectViewModel viewModel;

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private Wallet wallet;
    private List<WalletConnectSessionItem> wcSessions;
    private LinearLayout emptyBox;
    private FunctionButtonBar functionBar;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private int connectionCount = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        setContentView(R.layout.basic_list_activity);
        toolbar();
        setTitle(getString(R.string.title_wallet_connect));
        wallet = getIntent().getParcelableExtra(WALLET);
        initViewModel();

        emptyBox = findViewById(R.id.empty_box);

        functionBar = findViewById(R.id.layoutButtons);
        functionBar.setupFunctions(this, new ArrayList<>(Collections.singletonList(R.string.action_scan_dapp)));
    }

    private void initViewModel()
    {
        if (viewModel == null)
        {
            viewModel = new ViewModelProvider(this, viewModelFactory)
                    .get(WalletConnectViewModel.class);
            viewModel.serviceReady().observe(this, this::onServiceReady);
        }
    }

    private void onServiceReady(Boolean aBoolean)
    {
        //refresh adapter
        if (adapter != null)
        {
            adapter.notifyDataSetChanged();
        }
        else
        {
            setupList();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        stopConnectionCheck();
    }

    private void setupList()
    {
        wcSessions = viewModel.getSessions();

        if (wcSessions != null)
        {
            recyclerView = findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CustomAdapter();
            recyclerView.setAdapter(adapter);
//            recyclerView.addItemDecoration(new ListDivider(this));
            adapter.notifyDataSetChanged();
            if(wcSessions.size() > 0)
                emptyBox.setVisibility(View.GONE);
            else emptyBox.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        connectionCount = -1;
        initViewModel();
        setupList();
        startConnectionCheck();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scan_wc, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        else if (item.getItemId() == R.id.action_scan)
        {
            Intent intent = new Intent(this, QRScanner.class);
            intent.putExtra("wallet", wallet);
            intent.putExtra(C.EXTRA_UNIVERSAL_SCAN, true);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleClick(String action, int id)
    {
        Intent intent = new Intent(this, QRScanner.class);
        intent.putExtra("wallet", wallet);
        intent.putExtra(C.EXTRA_UNIVERSAL_SCAN, true);
        startActivity(intent);
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>
    {
        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_wc_session, parent, false);

            return new CustomAdapter.CustomViewHolder(itemView);
        }

        class CustomViewHolder extends RecyclerView.ViewHolder
        {
            final ImageView icon;
            final ImageView statusIcon;
            final TextView peerName;
            final TextView peerUrl;
            final LinearLayout clickLayer;
            final ChainName chainName;

            CustomViewHolder(View view)
            {
                super(view);
                icon = view.findViewById(R.id.icon);
                statusIcon = view.findViewById(R.id.status_icon);
                peerName = view.findViewById(R.id.session_name);
                peerUrl = view.findViewById(R.id.session_url);
                clickLayer = view.findViewById(R.id.item_layout);
                chainName = view.findViewById(R.id.chain_name);
            }
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position)
        {
            final WalletConnectSessionItem session = wcSessions.get(position);

            Glide.with(getApplication())
                    .load(session.icon)
                    .circleCrop()
                    .into(holder.icon);
            holder.peerName.setText(session.name);
            holder.peerUrl.setText(session.url);
            holder.chainName.setChainID(session.chainId);
            holder.clickLayer.setOnClickListener(v -> {
                //go to wallet connect session page
                Intent intent = new Intent(getApplication(), WalletConnectActivity.class);
                intent.putExtra("session", session.sessionId);
                startActivity(intent);
            });

            setupClient(session.sessionId, holder);

            holder.clickLayer.setOnLongClickListener(v -> {
                //delete this entry?
                dialogConfirmDelete(session, holder);
                return true;
            });
        }

        @Override
        public int getItemCount()
        {
            return wcSessions.size();
        }
    }

    private void setupClient(final String sessionId, final CustomAdapter.CustomViewHolder holder)
    {
        viewModel.getClient(this, sessionId, client -> handler.post(() -> {
            if (client == null || !client.isConnected())
            {
                holder.statusIcon.setVisibility(View.GONE);
            }
            else
            {
                holder.statusIcon.setVisibility(View.VISIBLE);
                holder.statusIcon.setImageResource(R.drawable.ic_connected);
            }
        }));
    }

    private void dialogConfirmDelete(WalletConnectSessionItem session, final CustomAdapter.CustomViewHolder holder)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setTitle(R.string.title_delete_session)
                .setMessage(getString(R.string.delete_session, session.name))
                .setPositiveButton(R.string.delete, (d, w) -> {
                    viewModel.deleteSession(session.sessionId);
                    viewModel.getClient(this, session.sessionId, client -> handler.post(() -> {
                        if (client == null || !client.isConnected())
                        {
                        }
                        else
                        {
                            client.killSession();
                        }
                    }));
                    setupList();
                })
                .setNegativeButton(R.string.action_cancel, (d, w) -> {
                    d.dismiss();
                })
                .setCancelable(false)
                .create();
        dialog.show();
    }

    private final BroadcastReceiver walletConnectChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(C.WALLET_CONNECT_COUNT_CHANGE))
            {
                handler.post(() -> adapter.notifyDataSetChanged());
                connectionCount = intent.getIntExtra("count", 0);
            }
        }
    };

    private void startConnectionCheck()
    {
        registerReceiver(walletConnectChangeReceiver, new IntentFilter(C.WALLET_CONNECT_COUNT_CHANGE));
    }

    private void stopConnectionCheck()
    {
        unregisterReceiver(walletConnectChangeReceiver);
    }
}
