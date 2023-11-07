package cofh.thermal.expansion.init.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.SOUND_EVENTS;

public class TExpSounds {

    private TExpSounds() {

    }

    public static void register() {


    }

    public static RegistryObject<SoundEvent> registerSound(String soundID) {

        return SOUND_EVENTS.register(soundID, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ID_THERMAL, soundID)));
    }

    // region IDs
    public static final String ID_SOUND_MACHINE_BOTTLER = "block.machine_bottler";
    public static final String ID_SOUND_MACHINE_CRUCIBLE = "block.machine_crucible";
    public static final String ID_SOUND_MACHINE_FURNACE = "block.machine_furnace";
    public static final String ID_SOUND_MACHINE_PULVERIZER = "block.machine_pulverizer";
    public static final String ID_SOUND_MACHINE_SAWMILL = "block.machine_sawmill";
    public static final String ID_SOUND_MACHINE_SMELTER = "block.machine_smelter";
    // endregion

    public static RegistryObject<SoundEvent> SOUND_MACHINE_BOTTLER = registerSound(ID_SOUND_MACHINE_BOTTLER);
    public static RegistryObject<SoundEvent> SOUND_MACHINE_CRUCIBLE = registerSound(ID_SOUND_MACHINE_CRUCIBLE);
    public static RegistryObject<SoundEvent> SOUND_MACHINE_FURNACE = registerSound(ID_SOUND_MACHINE_FURNACE);
    public static RegistryObject<SoundEvent> SOUND_MACHINE_PULVERIZER = registerSound(ID_SOUND_MACHINE_PULVERIZER);
    public static RegistryObject<SoundEvent> SOUND_MACHINE_SAWMILL = registerSound(ID_SOUND_MACHINE_SAWMILL);
    public static RegistryObject<SoundEvent> SOUND_MACHINE_SMELTER = registerSound(ID_SOUND_MACHINE_SMELTER);

}
