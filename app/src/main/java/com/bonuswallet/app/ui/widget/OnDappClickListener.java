package com.bonuswallet.app.ui.widget;

import java.io.Serializable;

import com.bonuswallet.app.entity.DApp;

public interface OnDappClickListener extends Serializable {
    void onDappClick(DApp dapp);
}
