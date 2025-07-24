package net.potato_modding.potatospells.datagen;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public record IVParser(ResourceLocation tag, Map<String, Double> bonuses) {}
