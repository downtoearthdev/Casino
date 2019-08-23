package com.scorchedcode.wolfplzz.Casino;

import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {

    public static Double MULTIPLIER;
    public Settings() {

    }

    //We would override load() if we extended YamlStaticConfig directly with createFile()

    @Override
    protected int getConfigVersion() {
        return 2;
    }

    private static void init() {
        MULTIPLIER = getConfig().getDouble("blackjack.payout-multiplier");
    }


}
