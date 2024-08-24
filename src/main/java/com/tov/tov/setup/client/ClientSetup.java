package com.tov.tov.setup.client;

import com.tov.tov.client.entity.renderer.SumiRenderer;
import com.tov.tov.client.entity.renderer.WilkorRenderer;
import com.tov.tov.setup.common.registries.TVEntities;
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
        EntityRenderers.register(TVEntities.SUMI.get(), SumiRenderer::new);
    }

    private ClientSetup(){}
}
