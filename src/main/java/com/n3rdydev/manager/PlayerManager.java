package com.n3rdydev.manager;

import com.google.common.collect.Maps;
import com.n3rdydev.models.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    public static HashMap<UUID, PlayerData> jogador = new HashMap<>();

    public HashMap<UUID, PlayerData> getPlayers() {
        return jogador;
    }

}
