package com.scorchedcode.wolfplzz.Casino.menu;

import com.scorchedcode.wolfplzz.Casino.CasinoEco;
import com.scorchedcode.wolfplzz.Casino.DarkInit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.ChatUtil;
import org.mineacademy.fo.PlayerUtil;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.InventoryDrawer;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;

public class AgeConfirmMenu extends Menu {

    private final Button yes = new YesButton();
    private final Button no = new NoButton();
    private Menu confirmMenu;
    public AgeConfirmMenu(Menu menu) {
        this.confirmMenu = menu;
        setSize(9);
        setTitle("Confirm you are age 18");
    }

    @Override
    protected String[] getInfo() {
        return null;
    }

    @Override
    public ItemStack getItemAt(int slot) {
        switch (slot) {
            case 0:
                return yes.getItem();
            case 8:
                return no.getItem();
            default:
                return Button.makeDummy(ItemCreator.of(CompMaterial.BLACK_STAINED_GLASS_PANE).color(CompColor.BLACK).name("").build()).getItem();
        }
    }

    class YesButton extends Button {

        @Override
        public void onClickedInMenu(Player player, Menu menu, ClickType click) {
            CasinoEco.getInstance().confirmedPlayers.add(player.getUniqueId());
            confirmMenu.displayTo(player);
        }

        @Override
        public ItemStack getItem() {
            return ItemCreator.of(CompMaterial.LIME_WOOL).color(CompColor.GREEN).name(ChatColor.GREEN + "YES").build().make();
        }
    }

    class NoButton extends Button {

        @Override
        public void onClickedInMenu(Player player, Menu menu, ClickType click) {
            player.closeInventory();
            player.sendMessage(ChatUtil.center(ChatColor.RED + "You must be >18 to gamble!"));
        }

        @Override
        public ItemStack getItem() {
            return ItemCreator.of(CompMaterial.RED_WOOL).color(CompColor.RED).name(ChatColor.RED + "NO").build().make();
        }
    }
}
