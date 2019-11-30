package me.morpheus.metropolis.api.config;

import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;

public interface ConfigService {

    GlobalConfig getGlobal();

    void reload() throws ObjectMappingException, IOException;

    void save() throws ObjectMappingException, IOException;

}
