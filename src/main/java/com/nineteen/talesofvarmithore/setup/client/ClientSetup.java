package com.nineteen.talesofvarmithore.setup.client;

import com.nineteen.talesofvarmithore.client.entity.renderer.WilkorRenderer;
import com.nineteen.talesofvarmithore.setup.common.registries.TVEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class ClientSetup {

    public static void onModCreate(IEventBus forgeBus, IEventBus modBus) {

    }

    public static void setup(final FMLClientSetupEvent event) {
        registerEntityRenderers();
    }

    public static void registerEntityRenderers() {
        EntityRenderers.register(TVEntities.WILKOR.get(), WilkorRenderer::new);
    }

    private ClientSetup(){}
}
