package com.scorchedcode.wolfplzz.Casino.command;

import com.scorchedcode.wolfplzz.Casino.menu.ChooseGameMenu;
import org.mineacademy.fo.command.SimpleSubCommand;

public class CasinoPlayCommand extends SimpleSubCommand {


    public CasinoPlayCommand() {
        super("play");
    }

    @Override
    protected void onCommand() {
        new ChooseGameMenu().displayTo(getPlayer());
    }
}
