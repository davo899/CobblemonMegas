package com.selfdot.cobblemonmegas.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.selfdot.cobblemonmegas.common.item.MegaStoneHeldItemManager;
import com.selfdot.cobblemonmegas.common.util.DisableableMod;
import com.selfdot.cobblemonmegas.common.util.JsonFile;

import java.util.HashSet;
import java.util.Set;

public class Config extends JsonFile {

    private final Set<String> megaStoneWhitelist = new HashSet<>();

    @Override
    protected JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        megaStoneWhitelist.forEach(jsonArray::add);
        jsonObject.add(DataKeys.MEGA_STONE_WHITELIST, jsonArray);
        return jsonObject;
    }

    public Config(DisableableMod mod) {
        super(mod);
    }

    public Set<String> getMegaStoneWhitelist() {
        return megaStoneWhitelist;
    }

    @Override
    protected String filename() {
        return "config/" + DataKeys.MOD_NAMESPACE + "/config.json";
    }

    @Override
    protected void setDefaults() {
        megaStoneWhitelist.clear();
        megaStoneWhitelist.addAll(MegaStoneHeldItemManager.getInstance().getAllMegaStoneIds());
    }

    @Override
    protected void loadFromJson(JsonElement jsonElement) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject.has(DataKeys.MEGA_STONE_WHITELIST)) {
            megaStoneWhitelist.clear();
            jsonObject.get(DataKeys.MEGA_STONE_WHITELIST).getAsJsonArray()
                .forEach(elem -> megaStoneWhitelist.add(elem.getAsString()));
        }
    }

}
