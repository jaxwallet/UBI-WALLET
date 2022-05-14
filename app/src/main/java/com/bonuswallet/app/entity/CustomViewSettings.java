package com.bonuswallet.app.entity;

import com.bonuswallet.app.C;
import com.bonuswallet.app.entity.tokens.Token;
import com.bonuswallet.app.entity.tokens.TokenCardMeta;
import com.bonuswallet.app.entity.tokens.TokenInfo;
import com.bonuswallet.ethereum.EthereumNetworkBase;

import java.util.Arrays;
import java.util.List;

import static com.bonuswallet.ethereum.EthereumNetworkBase.BINANCE_TEST_ID;
import static com.bonuswallet.ethereum.EthereumNetworkBase.MAINNET_ID;
import static com.bonuswallet.ethereum.EthereumNetworkBase.MATIC_ID;
import static com.bonuswallet.ethereum.EthereumNetworkBase.MATIC_TEST_ID;

public class CustomViewSettings
{
    public static final long primaryChain = MATIC_ID;
    public static final long primaryTestChain = BINANCE_TEST_ID;
    public static final String primaryChainName = C.ETHEREUM_NETWORK_NAME;

    //You can use the settings in this file to customise the wallet appearance

    //Ensures certain tokens are always visible, even if zero balance (see also 'showZeroBalance()' below).
    //See also lockedChains. You can also lock the chains that are displayed on.
    //If you leave the locked chains empty, the token will appear if the chain is selected
    private static final List<TokenInfo> lockedTokens = Arrays.asList(
            // new TokenInfo(String TokenAddress, String TokenName, String TokenSymbol, int TokenDecimals, boolean isEnabled, long ChainId)

            new TokenInfo("0x1d60aa1d6137dcb1306c8a901ebd215ca661d0cb", "Wrapped JAX", "WJAX", 4, true, EthereumNetworkBase.MATIC_ID),
            new TokenInfo("0x364044F4AeCF18699344eE43f9FD6A58C4BEA49c", "Wrapped JAX", "WJAX", 4, true, EthereumNetworkBase.MATIC_TEST_ID)
//
    );

    //List of chains that wallet can show
    //If blank, enable the user filter select dialog, if there are any entries here, the select network dialog is disabled
    //Note: you should always enable the chainId corresponding to the chainIDs in the lockedTokens.
    private static final List<Long> lockedChains = Arrays.asList(
//            EthereumNetworkBase.MAINNET_ID, //EG only show Main, xdai, classic and two testnets. Don't allow user to select any others
            //EthereumNetworkBase.XDAI_ID,
            //EthereumNetworkBase.RINKEBY_ID, //You can mix testnets and mainnets, but probably shouldn't as it may result in people getting scammed
            //EthereumNetworkBase.GOERLI_ID,
//            EthereumNetworkBase.BINANCE_MAIN_ID,
//            EthereumNetworkBase.AVALANCHE_ID
    );

    //TODO: Wallet can only show the above tokens
    private static final boolean onlyShowTheseTokens = true;

    public static List<TokenInfo> getLockedTokens()
    {
        return lockedTokens;
    }

    public static List<Long> getLockedChains()
    {
        return lockedChains;
    }

    //TODO: Not yet implemented; code will probably live in TokensService & TokenRealmSource
    public static boolean onlyShowLockedTokens()
    {
        return onlyShowTheseTokens;
    }

    //Does main wallet page show tokens with zero balance? NB: any 'Locked' tokens above will always be shown
    public static boolean showZeroBalance() { return false; }

    public static boolean tokenCanBeDisplayed(TokenCardMeta token)
    {

        return token.type == ContractType.ETHEREUM || token.isEnabled || isLockedToken(token.getChain(), token.getAddress());
    }

    public static boolean isLockedToken(long chainId, String contractAddress)
    {
        for (TokenInfo tInfo : lockedTokens)
        {
            if (tInfo.chainId == chainId && tInfo.address.equalsIgnoreCase(contractAddress)) return true;
        }

        return false;
    }

    public static ContractType checkKnownTokens(TokenInfo tokenInfo)
    {
        return ContractType.OTHER;
    }

    public static boolean showContractAddress(Token token)
    {
        return true;
    }

    public static long startupDelay()
    {
        return 0;
    }

    public static int getImageOverride()
    {
        return 0;
    }

    //Switch off dapp browser
    public static boolean hideDappBrowser()
    {
        return false;
    }

    //Hides the filter tab bar at the top of the wallet screen (ALL/CURRENCY/COLLECTIBLES)
    public static boolean hideTabBar()
    {
        return false;
    }

    //Use to switch off direct transfer, only use magiclink transfer
    public static boolean hasDirectTransfer()
    {
        return true;
    }

    //Allow multiple wallets (true) or single wallet mode (false)
    public static boolean canChangeWallets()
    {
        return true;
    }

    //Hide EIP681 generation (Payment request, generates a QR code another wallet user can scan to have all payment fields filled in)
    public static boolean hideEIP681() { return false; }

    //In main wallet menu, if wallet allows adding new tokens
    public static boolean canAddTokens() { return true; }

    //Implement minimal dappbrowser with no URL bar. You may want this if you want your browser to point to a specific website and only
    // allow navigation within that website
    // use this setting in conjunction with changing DEFAULT_HOMEPAGE in class EthereumNetworkBase
    public static boolean minimiseBrowserURLBar() { return false; }

    //Allow showing token management view
    public static boolean showManageTokens() { return true; }

    //Show all networks in Select Network screen. Set to `true` to show only filtered networks.
    public static boolean showAllNetworks() { return false; }

    public static String getDecimalFormat() { return "0.####E0"; }

    public static int getDecimalPlaces() { return 5; }

    //set if the Input Amount defaults to Fiat or Crypto
    public static boolean inputAmountFiatDefault()
    {
        return false;
    }
}
