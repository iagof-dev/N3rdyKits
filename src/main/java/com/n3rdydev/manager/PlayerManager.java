package com.n3rdydev.manager;

import com.google.common.collect.Maps;
import com.n3rdydev.models.PlayerData;

import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private Map<UUID, PlayerData> jogador = Maps.newConcurrentMap();

    public Map<UUID, PlayerData> getPlayers() {
        return jogador;
    }

}
