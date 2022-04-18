package com.bonuswallet.app.ui.widget;


import com.bonuswallet.app.entity.nftassets.NFTAsset;

import java.math.BigInteger;

public interface OnAssetSelectListener
{
    void onAssetSelected(BigInteger tokenId, NFTAsset asset, int position);
    void onAssetUnselected();
}
