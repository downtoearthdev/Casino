package com.scorchedcode.wolfplzz.Casino.command;

import com.scorchedcode.wolfplzz.Casino.Blackjack;
import com.scorchedcode.wolfplzz.Casino.DarkInit;
import org.bukkit.ChatColor;
import org.mineacademy.fo.ChatUtil;
import org.mineacademy.fo.command.SimpleSubCommand;

public class CasinoStatsCommand extends SimpleSubCommand {

    public CasinoStatsCommand() {
        super("stats");
    }

    @Override
    protected void onCommand() {
        getPlayer().sendMessage(ChatColor.GREEN + ChatUtil.center(ChatColor.GREEN + "Blackjack Stats", '=', ChatColor.GREEN));
        String player = getPlayer().getName();
        if(args.length == 1 && DarkInit.hasPerm(getPlayer(), "casino.admin"))
            player = getLastArg();
        getPlayer().sendMessage(ChatColor.DARK_GREEN + "Total Wins: " + ChatColor.GREEN + Blackjack.getWins(player));
        getPlayer().sendMessage(ChatColor.DARK_GREEN + "Total Losses: " + ChatColor.GREEN + Blackjack.getLosses(player));
        getPlayer().sendMessage(ChatColor.DARK_GREEN + "Total Won: " + ChatColor.GREEN + "$"+Blackjack.getWonAmount(player));
        getPlayer().sendMessage(ChatColor.DARK_GREEN + "Total Lost: " + ChatColor.GREEN + "$"+Blackjack.getLostAmount(player));
    }
}
