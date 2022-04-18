package com.bonuswallet.app.repository;

import com.bonuswallet.app.entity.NetworkInfo;

public interface OnNetworkChangeListener {
	void onNetworkChanged(NetworkInfo networkInfo);
}
