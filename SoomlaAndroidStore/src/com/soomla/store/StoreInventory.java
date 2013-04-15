package com.soomla.store;

import com.soomla.store.data.StorageManager;
import com.soomla.store.data.StoreInfo;
import com.soomla.store.domain.NonConsumableItem;
import com.soomla.store.domain.PurchasableVirtualItem;
import com.soomla.store.domain.VirtualItem;
import com.soomla.store.domain.virtualCurrencies.VirtualCurrency;
import com.soomla.store.domain.virtualGoods.EquippableVG;
import com.soomla.store.domain.virtualGoods.VirtualGood;
import com.soomla.store.exceptions.InsufficientFundsException;
import com.soomla.store.exceptions.VirtualItemNotFoundException;

public class StoreInventory {

    public static void buy(String itemId) throws InsufficientFundsException, VirtualItemNotFoundException {
        PurchasableVirtualItem pvi = (PurchasableVirtualItem) StoreInfo.getVirtualItem(itemId);
        pvi.buy(1);
    }

    /** Virtual Items **/

    // The itemId must be of a VirtualCurrency or SingleUseVG or LifetimeVG or EquippableVG
    public static int getVirtualItemBalance(String itemId) throws VirtualItemNotFoundException {
        VirtualItem item = StoreInfo.getVirtualItem(itemId);
        return StorageManager.getVirtualItemStorage(item).getBalance(item);
    }

    // The itemId must be of a VirtualCurrency or SingleUseVG or LifetimeVG or EquippableVG
    public static int addVirtualItemAmount(String itemId, int amount) throws VirtualItemNotFoundException{
        VirtualItem item = StoreInfo.getVirtualItem(itemId);
        return StorageManager.getVirtualItemStorage(item).add(item, amount);
    }

    // The itemId must be of a VirtualCurrency or SingleUseVG or LifetimeVG or EquippableVG
    public static int removeVirtualItemAmount(String itemId, int amount) throws VirtualItemNotFoundException{
        VirtualItem item = StoreInfo.getVirtualItem(itemId);
        return StorageManager.getVirtualItemStorage(item).remove(item, amount);
    }

    /** Virtual Goods **/

    public static void equipVirtualGood(String goodItemId) throws VirtualItemNotFoundException, ClassCastException{
        EquippableVG good = (EquippableVG) StoreInfo.getVirtualItem(goodItemId);

        StorageManager.getVirtualGoodsStorage().equip(good);
    }

    public static void unEquipVirtualGood(String goodItemId) throws VirtualItemNotFoundException, ClassCastException{
        EquippableVG good = (EquippableVG) StoreInfo.getVirtualItem(goodItemId);

        StorageManager.getVirtualGoodsStorage().unequip(good);
    }

    public static boolean isVirtualGoodEquipped(String goodItemId) throws VirtualItemNotFoundException, ClassCastException{
        EquippableVG good = (EquippableVG) StoreInfo.getVirtualItem(goodItemId);

        return StorageManager.getVirtualGoodsStorage().isEquipped(good);
    }

    /** NonConsumables **/

    public boolean nonConsumableItemExists(String nonConsItemId) throws VirtualItemNotFoundException, ClassCastException {
        NonConsumableItem nonConsumableItem = (NonConsumableItem) StoreInfo.getVirtualItem(nonConsItemId);

        return StorageManager.getNonConsumableItemsStorage().nonConsumableItemExists(nonConsumableItem);
    }

    public void addNonConsumableItem(String nonConsItemId) throws VirtualItemNotFoundException, ClassCastException {
        NonConsumableItem nonConsumableItem = (NonConsumableItem) StoreInfo.getVirtualItem(nonConsItemId);

        StorageManager.getNonConsumableItemsStorage().add(nonConsumableItem);
    }

    public void removeNonConsumableItem(String nonConsItemId) throws VirtualItemNotFoundException, ClassCastException {
        NonConsumableItem nonConsumableItem = (NonConsumableItem) StoreInfo.getVirtualItem(nonConsItemId);

        StorageManager.getNonConsumableItemsStorage().remove(nonConsumableItem);
    }
}
