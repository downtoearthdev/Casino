package com.scorchedcode.wolfplzz.Casino;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.mineacademy.fo.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Blackjack {

    private ArrayList<String> playerHand = new ArrayList<>();
    private ArrayList<String> dealerHand = new ArrayList<>();
    private Deck deck = new Deck();

    public Blackjack() {
        dealerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());

    }

    public ArrayList<String> getDealerHand() {
        return dealerHand;
    }

    public ArrayList<String> getPlayerHand() {
        return playerHand;
    }

    public void hitPlayer() {
        playerHand.add(deck.drawCard());
    }

    public void hitDealer() {
        dealerHand.add(deck.drawCard());
    }

    public PlayerType getWhoWon() {
        int dealer = getHandValue(PlayerType.DEALER);
        int player = getHandValue(PlayerType.PLAYER);
        if(player > 21)
            return PlayerType.DEALER;
        else if(dealer > 21)
            return PlayerType.PLAYER;
        else
            return (dealer > player) ? PlayerType.DEALER : PlayerType.PLAYER;
    }

    public boolean isBlackJack(PlayerType type) {
        return (getHandValue(type) == 21);
    }

    public boolean isDealerStanding() {
        return (getHandValue(PlayerType.DEALER) >= 17);
    }

    public boolean isPlayerBust() {
        return getHandValue(PlayerType.PLAYER) >21;
    }

    private int getHandValue(PlayerType type) {
        int value = 0;
        int aces = 0;
        ArrayList<String> hand = (type == PlayerType.DEALER) ? dealerHand : playerHand;
        for(String s : hand) {
            String number = s.split(" ")[0];
            if(number.equals("Jack") || number.equals("Queen") || number.equals("King"))
                value+=10;
            else if(number.equals("Ace"))
                aces++;
            else
                value+=Integer.valueOf(number);
        }
        for(int x = 0; x < aces; x++) {
            if(value <= 10)
                value+=11;
            else
                value+=1;
        }
        return value;
    }

    public static String getStats(String player) {
        String stats = "";
        stats+="Total Wins: " + getWins(player) + "\n";
        stats+="Total Losses: " + getLosses(player) + "\n";
        stats+="Total Won: $" + getWonAmount(player) + "\n";
        stats+="Total Lost: $" + getLostAmount(player);
        return stats;
    }


    public static int getWins(String player) {
        File saveFile = FileUtil.getOrMakeFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml");
        YamlConfiguration saveYaml = YamlConfiguration.loadConfiguration(saveFile);
        return saveYaml.getInt(player + ".wins", 0);
    }

    public static int getLosses(String player) {
        File saveFile = FileUtil.getOrMakeFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml");
        YamlConfiguration saveYaml = YamlConfiguration.loadConfiguration(saveFile);
        return saveYaml.getInt(player + ".losses", 0);
    }

    public static double getWonAmount(String player) {
        File saveFile = FileUtil.getOrMakeFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml");
        YamlConfiguration saveYaml = YamlConfiguration.loadConfiguration(saveFile);
        return saveYaml.getDouble(player + ".wonamount", 0.0);
    }

    public static double getLostAmount(String player) {
        File saveFile = FileUtil.getOrMakeFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml");
        YamlConfiguration saveYaml = YamlConfiguration.loadConfiguration(saveFile);
        return saveYaml.getDouble(player + ".lostamount", 0.0);
    }

    public static void addWin(String player) {
        File saveFile = FileUtil.getOrMakeFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml");
        YamlConfiguration saveYaml = YamlConfiguration.loadConfiguration(saveFile);
        saveYaml.set(player + ".wins", saveYaml.getInt(player + ".wins", 0)+1);
        try {
            saveYaml.save(FileUtil.getFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addLoss(String player) {
        File saveFile = FileUtil.getOrMakeFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml");
        YamlConfiguration saveYaml = YamlConfiguration.loadConfiguration(saveFile);
        saveYaml.set(player + ".losses", saveYaml.getInt(player + ".losses", 0)+1);
        try {
            saveYaml.save(FileUtil.getFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addAmountWon(String player, Double amount) {
        File saveFile = FileUtil.getOrMakeFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml");
        YamlConfiguration saveYaml = YamlConfiguration.loadConfiguration(saveFile);
        saveYaml.set(player + ".wonamount", saveYaml.getDouble(player + ".wonamount", 0.0)+amount);
        try {
            saveYaml.save(FileUtil.getFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addAmountLost(String player, Double amount) {
        File saveFile = FileUtil.getOrMakeFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml");
        YamlConfiguration saveYaml = YamlConfiguration.loadConfiguration(saveFile);
        saveYaml.set(player + ".lostamount", saveYaml.getDouble(player + ".lostamount", 0.0)+amount);
        try {
            saveYaml.save(FileUtil.getFile(CasinoEco.getInstance().getDataFolder().getPath() + File.separatorChar + "blackjack.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
