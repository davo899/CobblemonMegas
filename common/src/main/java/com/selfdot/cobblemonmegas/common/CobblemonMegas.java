package com.selfdot.cobblemonmegas.common;

public class CobblemonMegas {

    private static final CobblemonMegas INSTANCE = new CobblemonMegas();

    private CobblemonMegas() { }

    public static CobblemonMegas getInstance() {
        return INSTANCE;
    }

    public void onInitialize() { }

}
