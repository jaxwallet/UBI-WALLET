package com.bonuswallet.app.ui.widget;

import com.bonuswallet.app.entity.tokens.Token;

public interface OnTokenManageClickListener
{
    void onTokenClick(Token token, int position, boolean isChecked);
}
