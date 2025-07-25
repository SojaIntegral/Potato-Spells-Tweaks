package net.potato_modding.potatospells.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfigs {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec BUILDING;

    public static final ModConfigSpec.ConfigValue<Boolean> SHINY_GLOW;
    public static final ModConfigSpec.ConfigValue<Boolean> PLAYER_GLOW;

    static {
        BUILDER.push("Shiny");
        BUILDER.push("Glow");
        SHINY_GLOW = BUILDER.define("Enable shiny glow", true);
        BUILDER.comment("It's disabled already if the one above is false");
        PLAYER_GLOW = BUILDER.define("Enable Player glow", true);
        BUILDER.pop();

        BUILDING = BUILDER.build();
    }

}
