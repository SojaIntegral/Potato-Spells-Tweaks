package net.potato_modding.potatospells.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IHatePackets {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "potato_utils.json";

    private static File getFile() {
        File configDir = Minecraft.getInstance().gameDirectory; // .minecraft folder
        return new File(configDir, FILE_NAME);
    }

    public static void saveGUI(int overlayIndex) {
        JsonObject root = new JsonObject();
        root.addProperty("overlayIndex", overlayIndex);

        try (FileWriter writer = new FileWriter(getFile(), false)) {
            GSON.toJson(root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadGUI() {
        File file = getFile();
        if (!file.exists()) return 0;

        try (FileReader reader = new FileReader(file)) {
            JsonObject root = GSON.fromJson(reader, JsonObject.class);
            if (root.has("overlayIndex")) {
                return root.get("overlayIndex").getAsInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

