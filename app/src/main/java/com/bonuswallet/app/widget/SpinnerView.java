package com.bonuswallet.app.widget;

import android.content.Context;
import android.widget.RelativeLayout;

import com.bonuswallet.app.R;

public class SpinnerView extends RelativeLayout {
    public SpinnerView(Context context) {
        super(context);
        inflate(context, R.layout.item_spinner_view, this);
    }
}
