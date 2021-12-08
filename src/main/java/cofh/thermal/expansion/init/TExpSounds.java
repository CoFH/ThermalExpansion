package cofh.thermal.expansion.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.SOUND_EVENTS;

public class TExpSounds {

    private TExpSounds() {

    }

    public static void register() {

        registerSound(ID_SOUND_MACHINE_BOTTLER);
        registerSound(ID_SOUND_MACHINE_CRUCIBLE);
        registerSound(ID_SOUND_MACHINE_FURNACE);
        registerSound(ID_SOUND_MACHINE_PULVERIZER);
        registerSound(ID_SOUND_MACHINE_SAWMILL);
        registerSound(ID_SOUND_MACHINE_SMELTER);
    }

    public static void registerSound(String soundID) {

        SOUND_EVENTS.register(soundID, () -> new SoundEvent(new ResourceLocation(soundID)));
    }

    // region IDs
    public static final String ID_SOUND_MACHINE_BOTTLER = ID_THERMAL + ":block.machine_bottler";
    public static final String ID_SOUND_MACHINE_CRUCIBLE = ID_THERMAL + ":block.machine_crucible";
    public static final String ID_SOUND_MACHINE_FURNACE = ID_THERMAL + ":block.machine_furnace";
    public static final String ID_SOUND_MACHINE_PULVERIZER = ID_THERMAL + ":block.machine_pulverizer";
    public static final String ID_SOUND_MACHINE_SAWMILL = ID_THERMAL + ":block.machine_sawmill";
    public static final String ID_SOUND_MACHINE_SMELTER = ID_THERMAL + ":block.machine_smelter";
    // endregion

    // region REFERENCES
    @ObjectHolder(ID_SOUND_MACHINE_BOTTLER)
    public static final SoundEvent SOUND_MACHINE_BOTTLER = null;

    @ObjectHolder(ID_SOUND_MACHINE_CRUCIBLE)
    public static final SoundEvent SOUND_MACHINE_CRUCIBLE = null;

    @ObjectHolder(ID_SOUND_MACHINE_FURNACE)
    public static final SoundEvent SOUND_MACHINE_FURNACE = null;

    @ObjectHolder(ID_SOUND_MACHINE_PULVERIZER)
    public static final SoundEvent SOUND_MACHINE_PULVERIZER = null;

    @ObjectHolder(ID_SOUND_MACHINE_SAWMILL)
    public static final SoundEvent SOUND_MACHINE_SAWMILL = null;

    @ObjectHolder(ID_SOUND_MACHINE_SMELTER)
    public static final SoundEvent SOUND_MACHINE_SMELTER = null;
    // endregion
}
