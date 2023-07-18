package com.n3rdydev.manager;

import com.google.common.collect.Maps;
import com.n3rdydev.models.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private Map<UUID, PlayerData> jogador = new HashMap<>();
    public Map<UUID, PlayerData> getPlayers() {
        return jogador;
    }
    public synchronized void addPlayer(UUID playerId) {
        jogador.put(playerId, new PlayerData());
    }
}
