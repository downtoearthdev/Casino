package com.scorchedcode.wolfplzz.Casino.menu;

import com.scorchedcode.wolfplzz.Casino.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.model.HookManager;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;

public class BlackJackMenu extends Menu implements Bettable {

    private final Button hitButton = new HitButton();
    private final Button standButton = new StandButton();
    private final Button newRoundButton = new NewRoundButton();
    private final Button closeButton = new CloseButton();
    private Blackjack blackjack;
    private PlayerType whowon;
    private int betAmount = 0;

    public BlackJackMenu() {
        setSize(27);
        setTitle("BlackJack v" + DarkInit.pdf().getVersion());
        blackjack = new Blackjack();

    }

    private ItemStack updateInterface(int slot) {
        if(whowon != null || blackjack.isBlackJack(PlayerType.PLAYER) || blackjack.isBlackJack(PlayerType.DEALER)) {
            if(whowon == null) {
                whowon = blackjack.getWhoWon(); //Draw it right if we have blackjack on first turn
                provideJustDesserts(); //Make sure we sort payout
            }
            switch (slot) {
                case 9:
                    return newRoundButton.getItem();
                case 18:
                    return closeButton.getItem();
            }
        }
        else {
            switch (slot) {
                case 9:
                    return standButton.getItem();
                case 18:
                    return hitButton.getItem();
            }
        }
        for(String s : blackjack.getDealerHand()) {
            if(whowon == null && !(blackjack.getDealerHand().size() > 2) && (blackjack.getDealerHand().indexOf(s) == blackjack.getDealerHand().size()-1) && slot == 8-blackjack.getDealerHand().indexOf(s))
                return ItemCreator.of(CompMaterial.RED_CARPET).color(CompColor.RED).name("Unknown").build().make();
            else if(slot == 8-blackjack.getDealerHand().indexOf(s))
                return ItemCreator.of(CompMaterial.WHITE_CARPET).color(CompColor.WHITE).name(s).build().make();
        }
        for(String s : blackjack.getPlayerHand()) {
            if(slot == 26 - blackjack.getPlayerHand().indexOf(s))
                return ItemCreator.of(CompMaterial.WHITE_CARPET).color(CompColor.WHITE).name(s).build().make();
        }
        return (whowon != null) ? Button.makeDummy(ItemCreator.of(CompMaterial.BLACK_STAINED_GLASS_PANE).name((whowon == PlayerType.PLAYER) ? ChatColor.GREEN + "You've won!" : ChatColor.RED + "You've lost.").lore((whowon == PlayerType.PLAYER) ? "You've won " + Settings.MULTIPLIER + "x your bet." : "You've lost your bid. Please start a new round or close this screen.").build()).getItem() :
                Button.makeDummy(ItemCreator.of(CompMaterial.BLACK_STAINED_GLASS_PANE).name("").build()).getItem();
    }

    @Override
    public ItemStack getItemAt(int slot) {
        return updateInterface(slot);
    }

    @Override
    protected String[] getInfo() {
        return null;
    }

    @Override
    public void setBidAmount(int bid) {
        betAmount = bid;
    }

    private void provideJustDesserts() {
        if(whowon != null && whowon == PlayerType.PLAYER) {
            blackjack.addWin(getViewer().getName());
            blackjack.addAmountWon(getViewer().getName(), betAmount*Settings.MULTIPLIER);
            HookManager.deposit(getViewer(), betAmount * Settings.MULTIPLIER);
            getViewer().sendMessage(ChatColor.GREEN + "Congratulations! You won $" + betAmount* Settings.MULTIPLIER + " playing BlackJack!");
        }
        else if(whowon != null && whowon == PlayerType.DEALER) {
            blackjack.addLoss(getViewer().getName());
            blackjack.addAmountLost(getViewer().getName(), (double)betAmount);
            HookManager.withdraw(getViewer(), betAmount);
            getViewer().sendMessage(ChatColor.RED + "You have lost your bet amount of $" + betAmount);
        }
    }

    class HitButton extends Button {

        @Override
        public void onClickedInMenu(Player player, Menu menu, ClickType click) {
            blackjack.hitPlayer();
            if(blackjack.isPlayerBust()) {
                whowon = blackjack.getWhoWon();
                provideJustDesserts();
            }
            restartMenu("Hit!");
        }

        @Override
        public ItemStack getItem() {
            return ItemCreator.of(CompMaterial.LIME_WOOL).color(CompColor.GREEN).name(ChatColor.YELLOW + "Hit!" ).build().make();
        }
    }

    class StandButton extends Button {

        @Override
        public void onClickedInMenu(Player player, Menu menu, ClickType click) {
            while(!blackjack.isDealerStanding())
                blackjack.hitDealer();
            whowon = blackjack.getWhoWon();
            provideJustDesserts();
            restartMenu();
        }

        @Override
        public ItemStack getItem() {
            return ItemCreator.of(CompMaterial.RED_WOOL).color(CompColor.RED).name(ChatColor.YELLOW + "Stand!" ).build().make();
        }
    }

    class CloseButton extends Button {

        @Override
        public void onClickedInMenu(Player player, Menu menu, ClickType click) {
            player.closeInventory();
        }

        @Override
        public ItemStack getItem() {
            return ItemCreator.of(CompMaterial.RED_WOOL).color(CompColor.RED).name(ChatColor.YELLOW + "Close" ).build().make();
        }
    }

    class NewRoundButton extends Button {

        @Override
        public void onClickedInMenu(Player player, Menu menu, ClickType click) {
            player.closeInventory();
            new BetMenu(new BlackJackMenu()).displayTo(player);
        }

        @Override
        public ItemStack getItem() {
            return ItemCreator.of(CompMaterial.YELLOW_WOOL).color(CompColor.YELLOW).name(ChatColor.YELLOW + "New Round").build().make();
        }
    }
}
