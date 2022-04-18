package com.bonuswallet.app.ui.widget;

import android.view.View;

import com.bonuswallet.token.entity.MagicLinkData;

/**
 * Created by James on 21/02/2018.
 */

public interface OnSalesOrderClickListener {
    void onOrderClick (View view, MagicLinkData range);
}
