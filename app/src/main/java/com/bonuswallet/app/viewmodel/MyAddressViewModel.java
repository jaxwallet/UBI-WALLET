package com.bonuswallet.app.viewmodel;

import com.bonuswallet.app.entity.NetworkInfo;
import com.bonuswallet.app.repository.EthereumNetworkRepositoryType;
import com.bonuswallet.app.service.AssetDefinitionService;
import com.bonuswallet.app.service.TokensService;

public class MyAddressViewModel extends BaseViewModel {
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;
    private final TokensService tokenService;
    private final AssetDefinitionService assetDefinitionService;

    MyAddressViewModel(
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokensService tokensService,
            AssetDefinitionService assetDefinitionService) {
        this.ethereumNetworkRepository = ethereumNetworkRepository;
        this.tokenService = tokensService;
        this.assetDefinitionService = assetDefinitionService;
    }

    public TokensService getTokenService() {
        return tokenService;
    }

    public EthereumNetworkRepositoryType getEthereumNetworkRepository() {
        return ethereumNetworkRepository;
    }

    public NetworkInfo getNetworkByChain(long chainId) {
        return ethereumNetworkRepository.getNetworkByChain(chainId);
    }

    public AssetDefinitionService getAssetDefinitionService()
    {
        return assetDefinitionService;
    }
}
