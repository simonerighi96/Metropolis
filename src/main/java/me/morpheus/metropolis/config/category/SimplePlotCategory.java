package me.morpheus.metropolis.config.category;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import me.morpheus.metropolis.api.config.PlotCategory;
import me.morpheus.metropolis.api.flag.Flag;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class SimplePlotCategory implements PlotCategory {

    @Setting(value = "default-perm") private int defPerm;
    @Setting private Object2IntOpenHashMap<Flag> unowned = new Object2IntOpenHashMap<>();

    {
        this.unowned.defaultReturnValue(Integer.MIN_VALUE);
    }

    @Override
    public int getUnownedPermission(Flag flag) {
        int perm = this.unowned.getInt(flag);
        if (perm == Integer.MIN_VALUE) {
            return this.defPerm;
        }
        return perm;
    }

}
