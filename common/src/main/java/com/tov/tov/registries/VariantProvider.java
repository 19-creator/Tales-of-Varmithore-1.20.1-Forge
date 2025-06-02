package com.tov.tov.registries;

import software.bluelib.api.entity.variant.IVariantProvider;
import software.bluelib.api.registry.builders.entity.EntityBuilder;

import java.util.List;

public class VariantProvider implements IVariantProvider {

    @Override
    public List<String> getEntityNames() {
        return EntityBuilder.getDragonNames();
    }

    @Override
    public String getBasePath() {
        return "entities/";
    }
}
