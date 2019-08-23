package com.scorchedcode.wolfplzz.Casino.command;

import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommandGroup;

public class CasinoCommandGroup extends SimpleCommandGroup {

    @Override
    protected void registerSubcommands() {
        registerSubcommand(new CasinoPlayCommand());
        registerSubcommand(new CasinoStatsCommand());
        registerHelpLine("/casino <play | stats> [playerName]");
    }
}
