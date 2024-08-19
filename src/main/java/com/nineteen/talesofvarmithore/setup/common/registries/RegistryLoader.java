package com.nineteen.talesofvarmithore.setup.common.registries;

import net.minecraftforge.eventbus.api.IEventBus;

public final class RegistryLoader {

    public static void load(IEventBus forgeBus, IEventBus modBus) {
        TVEntities.ENTITY_TYPES.register(modBus);
        TVItems.ITEMS.register(modBus);
    }

    private RegistryLoader(){}
}
