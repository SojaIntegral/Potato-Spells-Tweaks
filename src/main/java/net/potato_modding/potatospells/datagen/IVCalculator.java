package net.potato_modding.potatospells.datagen;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.*;

public class IVCalculator extends SimpleJsonResourceReloadListener {
    public static final IVCalculator INSTANCE = new IVCalculator();
    private static final Gson GSON = new Gson();
    private final List<IVParser> bonusList = new ArrayList<>();

    private IVCalculator() {
        super(GSON, "ivcalc/race_bonuses.json");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profiler) {
        bonusList.clear();
        for (JsonElement element : map.values()) {
            JsonArray array = element.getAsJsonArray();
            for (JsonElement entry : array) {
                JsonObject obj = entry.getAsJsonObject();
                if (!obj.has("tag") || !obj.has("bonuses")) continue;

                String tagStr = obj.get("tag").getAsString();
                if (tagStr == null || tagStr.isEmpty()) {
                    continue;
                }

                ResourceLocation tag;
                try {
                    tag = ResourceLocation.tryParse(tagStr);
                    if (tag == null) {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }

                JsonObject bonusObj = obj.getAsJsonObject("bonuses");
                Map<String, Double> bonuses = new HashMap<>();
                for (var key : bonusObj.keySet()) {
                    try {
                        bonuses.put(key, bonusObj.get(key).getAsDouble());
                    } catch (Exception e) {
                    }
                }
                bonusList.add(new IVParser(tag, bonuses));
            }
        }
    }


    public Optional<Map<String, Double>> getBonusesFor(LivingEntity entity) {
        for (IVParser bonus : bonusList) {
            TagKey<EntityType<?>> tagKey = TagKey.create(Registries.ENTITY_TYPE, bonus.tag());
            if (entity.getType().is(tagKey)) {
                return Optional.of(bonus.bonuses());
            }
        }
        return Optional.empty();
    }
}