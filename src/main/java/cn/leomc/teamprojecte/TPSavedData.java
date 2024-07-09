package cn.leomc.teamprojecte;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TPSavedData extends SavedData {

    public static TPSavedData getData(UUID teamId) {
        return ServerLifecycleHooks.getCurrentServer().overworld().getDataStorage()
                .computeIfAbsent(TPSavedData::new, TPSavedData::new, "teamprojecte" + File.separator + teamId);
    }

    @Nullable
    public TPTeam team = null;

    TPSavedData() {
    }

    TPSavedData(CompoundTag tag) {
        TeamProjectE.LOGGER.debug(tag.toString());
        String version = tag.getString("version");
        this.team = new TPTeam(tag.getCompound("team"), version);
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag) {
        if(this.team != null) {
            tag.putString("version", "1");
            tag.put("team", this.team.save());
        }

        return tag;
    }
}
