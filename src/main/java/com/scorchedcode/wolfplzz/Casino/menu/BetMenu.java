package com.scorchedcode.wolfplzz.Casino.menu;

import com.scorchedcode.wolfplzz.Casino.Bettable;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.InventoryDrawer;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.model.HookManager;
import org.mineacademy.fo.remain.CompMaterial;

import java.util.ArrayList;

public class BetMenu extends Menu {

    private final Button whiteChip = new ChipButton(ChipColor.WHITE);
    private final Button pinkChip = new ChipButton(ChipColor.PINK);
    private final Button redChip = new ChipButton(ChipColor.RED);
    private final Button blueChip = new ChipButton(ChipColor.BLUE);
    private final Button yellowChip = new ChipButton(ChipColor.YELLOW);
    private final Button greenChip = new ChipButton(ChipColor.GREEN);
    private final Button blackChip = new ChipButton(ChipColor.BLACK);
    private final Button continueChip = new ChipButton(ChipColor.CONTINUE);
    private final ArrayList<Button> chipButtons = new ArrayList<>();
    private Bettable openMenu;
    private boolean isLoaded = false;
    public BetMenu(Bettable openMenu) {
        this.openMenu = openMenu;
        setSize(9);
        setTitle("Place your bet!");
        chipButtons.add(whiteChip);
        chipButtons.add(pinkChip);
        chipButtons.add(redChip);
        chipButtons.add(blueChip);
        chipButtons.add(yellowChip);
        chipButtons.add(greenChip);
        chipButtons.add(blackChip);
    }

    @Override
    public ItemStack getItemAt(int slot) {
        switch (slot) {
            case 0:
                return whiteChip.getItem();
            case 1:
                return pinkChip.getItem();
            case 2:
                return redChip.getItem();
            case 3:
                return blueChip.getItem();
            case 4:
                return yellowChip.getItem();
            case 5:
                return greenChip.getItem();
            case 6:
                return blackChip.getItem();
            case 8:
                return continueChip.getItem();
            default:
                return Button.makeEmpty().getItem();
        }
    }

    @Override
    protected String[] getInfo() {
        return null;
    }

    @Override
    protected void onDisplay(InventoryDrawer drawer) {
        isLoaded = true;
    }

    private int tallyBetAmount() {
        int tally = 0;
            for (Button b : chipButtons)
                tally += (((ChipButton)b).betValue * ((ChipButton)b).value.getValue());
        return tally;
    }

    class ChipButton extends Button {
        public ChipColor value;
        public int betValue = 0;
        public ChipButton(ChipColor value) {
            this.value = value;
        }

        @Override
        public void onClickedInMenu(Player player, Menu menu, ClickType click) {
            if(value == ChipColor.CONTINUE) {
                player.closeInventory();
                openMenu.setBidAmount(tallyBetAmount());
                ((Menu)openMenu).displayTo(player);
            }
            else if(click.isLeftClick()) {
                if(HookManager.getBalance(player) >= value.getValue()+tallyBetAmount()) {
                    betValue++;
                    restartMenu();
                }
            }
            else if(click.isRightClick()) {
                if(betValue != 0) {
                    betValue--;
                    restartMenu();
                }
            }
        }

        @Override
        public ItemStack getItem() {
            switch(value.getValue()) {
                case 1:
                    return (betValue == 0) ? ItemCreator.of(CompMaterial.WHITE_STAINED_GLASS).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make() :
                            ItemCreator.of(CompMaterial.WHITE_WOOL).amount(betValue).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make();
                case 2:
                    return (betValue == 0) ? ItemCreator.of(CompMaterial.PINK_STAINED_GLASS).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make() :
                            ItemCreator.of(CompMaterial.PINK_WOOL).amount(betValue).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make();
                case 5:
                    return (betValue == 0) ? ItemCreator.of(CompMaterial.RED_STAINED_GLASS).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make() :
                            ItemCreator.of(CompMaterial.RED_WOOL).amount(betValue).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make();
                case 10:
                    return (betValue == 0) ? ItemCreator.of(CompMaterial.BLUE_STAINED_GLASS).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make() :
                            ItemCreator.of(CompMaterial.BLUE_WOOL).amount(betValue).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make();
                case 20:
                    return (betValue == 0) ? ItemCreator.of(CompMaterial.YELLOW_STAINED_GLASS).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make() :
                            ItemCreator.of(CompMaterial.YELLOW_WOOL).amount(betValue).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make();
                case 25:
                    return (betValue == 0) ? ItemCreator.of(CompMaterial.GREEN_STAINED_GLASS).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make() :
                            ItemCreator.of(CompMaterial.GREEN_WOOL).amount(betValue).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make();
                case 100:
                    return (betValue == 0) ? ItemCreator.of(CompMaterial.BLACK_STAINED_GLASS).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make() :
                            ItemCreator.of(CompMaterial.BLACK_WOOL).amount(betValue).name((HookManager.getBalance(getViewer()) >= value.getValue()+tallyBetAmount() ? ChatColor.YELLOW : ChatColor.RED) + "$" + value.getValue() + " chip").build().make();
                default:
                    return ItemCreator.of(CompMaterial.GOLD_INGOT).name(ChatColor.YELLOW + "Bet $" + tallyBetAmount()).build().make();
            }
        }
    }

    enum ChipColor {
        WHITE(1),
        PINK(2),
        RED(5),
        BLUE(10),
        YELLOW(20),
        GREEN(25),
        BLACK(100),
        CONTINUE(0);

        private int value;
        ChipColor(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }
    }
}
