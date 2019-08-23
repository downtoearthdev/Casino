package com.scorchedcode.wolfplzz.Casino;

import com.scorchedcode.wolfplzz.Casino.command.CasinoCommandGroup;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class CasinoEco extends SimplePlugin {
    public HashSet<UUID> confirmedPlayers = new HashSet<>();
    public double multiplier = 1.5;
    private static CasinoEco instance;
    CasinoCommandGroup mainCommand = new CasinoCommandGroup();

    @Override
    public List<Class<? extends YamlStaticConfig>> getSettings() {
        List<Class<? extends YamlStaticConfig>> settings = new ArrayList<>();
        settings.add(Settings.class);
        return settings;
    }

    @Override
    public void onPluginStop() {

    }

    @Override
    public void onPluginStart() {
        new DarkInit(this);
        instance = this;
    }

    @Override
    public void onReloadablesStart() {
        //How we would register other command groups - getMainCommand().register("casino", null);

        multiplier = Settings.MULTIPLIER;
    }

    @Override //Required to have a main command group, alias defined in settings.yml
    public SimpleCommandGroup getMainCommand() {
        return mainCommand;
    }

    public static CasinoEco getInstance() {
        return instance;
    }
}
