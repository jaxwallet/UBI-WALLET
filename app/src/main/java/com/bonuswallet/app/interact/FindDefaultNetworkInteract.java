package com.bonuswallet.app.interact;

import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.entity.NetworkInfo;

public class FindDefaultNetworkInteract {

    private final EthereumNetworkRepositoryType ethereumNetworkRepository;

    public FindDefaultNetworkInteract(EthereumNetworkRepositoryType ethereumNetworkRepository) {
        this.ethereumNetworkRepository = ethereumNetworkRepository;
    }

    public String getNetworkName(long chainId)
    {
        return ethereumNetworkRepository.getNetworkByChain(chainId).getShortName();
    }

    public NetworkInfo getNetworkInfo(long chainId)
    {
        return ethereumNetworkRepository.getNetworkByChain(chainId);
    }
}
