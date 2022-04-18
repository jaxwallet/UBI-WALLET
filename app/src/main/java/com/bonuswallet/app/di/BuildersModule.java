package com.bonuswallet.app.di;

import com.bonuswallet.app.ui.ActivityFragment;
import com.bonuswallet.app.ui.AddCustomRPCNetworkActivity;
import com.bonuswallet.app.ui.AddTokenActivity;
import com.bonuswallet.app.ui.AdvancedSettingsActivity;
import com.bonuswallet.app.ui.AssetDisplayActivity;
import com.bonuswallet.app.ui.BackupKeyActivity;
import com.bonuswallet.app.ui.DappBrowserFragment;
import com.bonuswallet.app.ui.Erc1155Activity;
import com.bonuswallet.app.ui.Erc1155AssetDetailActivity;
import com.bonuswallet.app.ui.Erc1155AssetListActivity;
import com.bonuswallet.app.ui.Erc1155AssetSelectActivity;
import com.bonuswallet.app.ui.Erc1155AssetsFragment;
import com.bonuswallet.app.ui.Erc1155InfoFragment;
import com.bonuswallet.app.ui.Erc20DetailActivity;
import com.bonuswallet.app.ui.FunctionActivity;
import com.bonuswallet.app.ui.GasSettingsActivity;
import com.bonuswallet.app.ui.HelpActivity;
import com.bonuswallet.app.ui.HomeActivity;
import com.bonuswallet.app.ui.ImportTokenActivity;
import com.bonuswallet.app.ui.ImportWalletActivity;
import com.bonuswallet.app.ui.MyAddressActivity;
import com.bonuswallet.app.ui.NameThisWalletActivity;
import com.bonuswallet.app.ui.NewSettingsFragment;
import com.bonuswallet.app.ui.RedeemAssetSelectActivity;
import com.bonuswallet.app.ui.RedeemSignatureDisplayActivity;
import com.bonuswallet.app.ui.SelectNetworkActivity;
import com.bonuswallet.app.ui.SelectNetworkFilterActivity;
import com.bonuswallet.app.ui.SellDetailActivity;
import com.bonuswallet.app.ui.SendActivity;
import com.bonuswallet.app.ui.SplashActivity;
import com.bonuswallet.app.ui.TokenActivity;
import com.bonuswallet.app.ui.TokenActivityFragment;
import com.bonuswallet.app.ui.TokenDetailActivity;
import com.bonuswallet.app.ui.TokenFunctionActivity;
import com.bonuswallet.app.ui.TokenManagementActivity;
import com.bonuswallet.app.ui.TokenScriptManagementActivity;
import com.bonuswallet.app.ui.TransactionDetailActivity;
import com.bonuswallet.app.ui.TransactionSuccessActivity;
import com.bonuswallet.app.ui.TransferNFTActivity;
import com.bonuswallet.app.ui.TransferTicketDetailActivity;
import com.bonuswallet.app.ui.WalletActionsActivity;
import com.bonuswallet.app.ui.WalletConnectActivity;
import com.bonuswallet.app.ui.WalletConnectSessionActivity;
import com.bonuswallet.app.ui.WalletFragment;
import com.bonuswallet.app.ui.WalletsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
	@ActivityScope
	@ContributesAndroidInjector(modules = SplashModule.class)
	abstract SplashActivity bindSplashModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = AccountsManageModule.class)
	abstract WalletsActivity bindManageWalletsModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = ImportModule.class)
	abstract ImportWalletActivity bindImportWalletModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = TransactionDetailModule.class)
	abstract TransactionDetailActivity bindTransactionDetailModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = SendModule.class)
	abstract SendActivity bindSendModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = TransactionSuccessModule.class)
	abstract TransactionSuccessActivity bindTransactionSuccessModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = GasSettingsModule.class)
	abstract GasSettingsActivity bindGasSettingsModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = AddTokenModule.class)
	abstract AddTokenActivity bindAddTokenActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = RedeemSignatureDisplayModule.class)
	abstract RedeemSignatureDisplayActivity bindSignatureDisplayActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract AssetDisplayActivity bindAssetDisplayActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = SellDetailModule.class)
	abstract SellDetailActivity bindSellDetailsActivity();

	@FragmentScope
	@ContributesAndroidInjector(modules = NewSettingsModule.class)
	abstract NewSettingsFragment bindNewSettingsFragment();

	@FragmentScope
	@ContributesAndroidInjector(modules = ActivityModule.class)
	abstract ActivityFragment bindActivityFragment();

	@ActivityScope
	@ContributesAndroidInjector(modules = RedeemAssetSelectModule.class)
	abstract RedeemAssetSelectActivity bindRedeemTokenSelectActivity();

	@FragmentScope
	@ContributesAndroidInjector(modules = WalletModule.class)
	abstract WalletFragment bindWalletFragment();

	@ActivityScope
	@ContributesAndroidInjector(modules = HomeModule.class)
	abstract HomeActivity bindHomeActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = ImportTokenModule.class)
	abstract ImportTokenActivity bindImportTokenActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TransferTicketDetailModule.class)
	abstract TransferTicketDetailActivity bindTransferTicketDetailActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = HelpModule.class)
	abstract HelpActivity bindHelpActivity();

	@FragmentScope
	@ContributesAndroidInjector(modules = DappBrowserModule.class)
	abstract DappBrowserFragment bindDappBrowserFragment();

	@ActivityScope
	@ContributesAndroidInjector(modules = Erc20DetailModule.class)
	abstract Erc20DetailActivity bindErc20DetailActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = WalletActionsModule.class)
	abstract WalletActionsActivity bindWalletActionsActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = BackupKeyModule.class)
	abstract BackupKeyActivity bindBackupKeyActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = MyAddressModule.class)
	abstract MyAddressActivity bindMyAddressActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract TokenFunctionActivity bindTokenFunctionActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract FunctionActivity bindFunctionActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract TokenDetailActivity bindTokenDetailActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = TokenFunctionModule.class)
	abstract TokenActivity bindTokenActivity();

	@ContributesAndroidInjector(modules = SelectNetworkModule.class)
	abstract SelectNetworkActivity bindSelectNetworkActivity();

	@ContributesAndroidInjector(modules = CustomNetworkModule.class)
	abstract AddCustomRPCNetworkActivity bindAddCustomRPCNetworkActivity();

	@ContributesAndroidInjector(modules = SelectNetworkFilterModule.class)
	abstract SelectNetworkFilterActivity bindSelectNetworkFilterActivity();

	@ContributesAndroidInjector(modules = TokenManagementModule.class)
	abstract TokenManagementActivity bindTokenManagementActivity();

	@ContributesAndroidInjector(modules = AdvancedSettingsModule.class)
	abstract AdvancedSettingsActivity bindAdvancedSettingsActivity();

    @ActivityScope
	@ContributesAndroidInjector(modules = TokenScriptManagementModule.class)
	abstract TokenScriptManagementActivity bindTokenScriptManagementActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = WalletConnectModule.class)
	abstract WalletConnectActivity bindWalletConnectActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = WalletConnectModule.class)
	abstract WalletConnectSessionActivity bindWalletConnectSessionActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = Erc1155Module.class)
    abstract Erc1155Activity bindErc1155Activity();

    @ActivityScope
    @ContributesAndroidInjector(modules = Erc1155AssetDetailModule.class)
    abstract Erc1155AssetDetailActivity bindErc1155AssetDetailActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = Erc1155InfoModule.class)
    abstract Erc1155InfoFragment bindErc1155InfoFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = Erc1155AssetsModule.class)
    abstract Erc1155AssetsFragment bindErc1155AssetsFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = Erc1155AssetSelectModule.class)
    abstract Erc1155AssetSelectActivity bindErc1155AssetSelectActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = Erc1155AssetListModule.class)
    abstract Erc1155AssetListActivity bindErc1155AssetListActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = TokenActivityModule.class)
    abstract TokenActivityFragment bindTokenActivityFragment();

	@FragmentScope
	@ContributesAndroidInjector(modules = TransferTicketDetailModule.class)
	abstract TransferNFTActivity bindTransferNFTActivity();

	@ActivityScope
	@ContributesAndroidInjector(modules = NameThisWalletModule.class)
	abstract NameThisWalletActivity bindNameThisWalletActivity();
}
