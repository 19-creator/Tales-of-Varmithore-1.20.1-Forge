package com.nineteen.talesofvarmithore.setup.datagen;

import com.nineteen.talesofvarmithore.TVModInfo;
import com.nineteen.talesofvarmithore.setup.common.registries.TVItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TVModInfo.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        spawnEgg(TVItems.WILKOR_SPAWN_EGG);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture(
                        "layer0",
                        new ResourceLocation(TVModInfo.MOD_ID, "item/" + item.getId().getPath())
                );

    }

    private ItemModelBuilder spawnEgg(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

}
