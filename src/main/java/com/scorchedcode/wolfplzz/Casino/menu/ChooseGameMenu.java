package com.scorchedcode.wolfplzz.Casino.menu;

import com.scorchedcode.wolfplzz.Casino.CasinoEco;
import com.scorchedcode.wolfplzz.Casino.DarkInit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.button.ButtonMenu;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;

public class ChooseGameMenu extends Menu {

    private final ButtonMenu blackJackAgeButton = new ButtonMenu(new AgeConfirmMenu(new BetMenu(new BlackJackMenu())), CompMaterial.DARK_PRISMARINE,
            ChatColor.GREEN + "BlackJack " + ChatColor.RED + "(18+)", "Try your luck at 21!");
    private final ButtonMenu blackJackButton = new ButtonMenu(new BetMenu(new BlackJackMenu()), CompMaterial.DARK_PRISMARINE,
            ChatColor.GREEN + "BlackJack (18+)", "Try your luck at 21!");
    public ChooseGameMenu() {
        setSize(9);
        setTitle("Choose a game!");
    }

    @Override
    public ItemStack getItemAt(int slot) {
        switch(slot) {
            case 0:
                if(DarkInit.hasPerm(getViewer(), "casino.ageconfirmed") || CasinoEco.getInstance().confirmedPlayers.contains(getViewer().getUniqueId()))
                    return blackJackButton.getItem();
                else
                    return blackJackAgeButton.getItem();
            default:
                return Button.makeDummy(ItemCreator.of(CompMaterial.BLACK_STAINED_GLASS_PANE).color(CompColor.BLACK).name("").build()).getItem();
        }
    }

    @Override
    protected String[] getInfo() {
        return null;
    }

}
