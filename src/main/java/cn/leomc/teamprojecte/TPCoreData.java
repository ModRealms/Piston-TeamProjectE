package cn.leomc.teamprojecte;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TPCoreData extends SavedData {

    private static TPCoreData DATA;

    public static TPCoreData getData() {
        if (DATA == null && ServerLifecycleHooks.getCurrentServer() != null)
            DATA = ServerLifecycleHooks.getCurrentServer().overworld().getDataStorage()
                    .computeIfAbsent(TPCoreData::new, TPCoreData::new, "teamprojecte");
        return DATA;
    }

    public final Map<UUID, TPTeam> teams = new HashMap<>();
    public final Map<UUID, UUID> playerTeamCache = new HashMap<>();

    public static void onServerStopped() {
        DATA = null;
    }

    public  void invalidateCache(UUID uuid) {
        playerTeamCache.remove(uuid);
    }

    TPCoreData() {
    }

    TPCoreData(CompoundTag tag) {
        TeamProjectE.LOGGER.debug(tag.toString());
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag) {
        return tag;
    }
}
