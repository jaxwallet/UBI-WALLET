package com.bonuswallet.app.ui.widget.holder;

import static com.bonuswallet.app.C.EXTRA_ADDRESS;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bonuswallet.app.R;
import com.bonuswallet.app.ui.TokenManagementActivity;
import com.bonuswallet.app.ui.widget.entity.ManageTokensData;

public class ManageTokensHolder extends BinderViewHolder<ManageTokensData> {
    public static final int VIEW_TYPE = 2015;

    LinearLayout layout;

    @Override
    public void bind(@Nullable ManageTokensData data, @NonNull Bundle addition) {
        layout.setOnClickListener(v -> {
            if (data.walletAddress != null && data.launcher != null) {
                Intent intent = new Intent(getContext(), TokenManagementActivity.class);
                intent.putExtra(EXTRA_ADDRESS, data.walletAddress);
                data.launcher.launch(intent);
            }
        });
    }

    public ManageTokensHolder(int res_id, ViewGroup parent) {
        super(res_id, parent);
        layout = findViewById(R.id.layout_manage_tokens_button);
    }
}
