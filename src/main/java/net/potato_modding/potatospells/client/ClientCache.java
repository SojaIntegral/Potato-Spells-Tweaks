package net.potato_modding.potatospells.client;

import java.util.HashMap;
import java.util.Map;

public class ClientCache {
    private static final Map<Integer, double[]> CACHE = new HashMap<>();

    public static void put(int entityId, double[] ivs) {
        CACHE.put(entityId, ivs);
    }

    public static double[] get(int entityId) {
        return CACHE.getOrDefault(entityId, new double[8]);
    }

    public static boolean has(int entityId) {
        return CACHE.containsKey(entityId);
    }

    public static void clear(int entityId) {
        CACHE.remove(entityId);
    }

    public static void clearAll() {
        CACHE.clear();
    }
}
