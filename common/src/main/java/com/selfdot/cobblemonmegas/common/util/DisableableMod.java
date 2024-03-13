package com.selfdot.cobblemonmegas.common.util;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public abstract class DisableableMod {

    private boolean disabled = false;
    private final Logger LOGGER = LogUtils.getLogger();

    public boolean isDisabled() {
        return disabled;
    }

    public Logger getLogger() {
        return LOGGER;
    }

    public void disable() {
        this.disabled = true;
    }

}
