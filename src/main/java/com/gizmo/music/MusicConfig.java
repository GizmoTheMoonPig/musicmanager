package com.gizmo.music;

import net.minecraftforge.common.ForgeConfigSpec;

public class MusicConfig {

	public final ForgeConfigSpec.BooleanValue displayRecordToast;
	public final ForgeConfigSpec.BooleanValue silentMusicToasts;
	public final ForgeConfigSpec.IntValue minSongDelay;
	public final ForgeConfigSpec.IntValue maxSongDelay;

	public MusicConfig(ForgeConfigSpec.Builder builder) {
		this.displayRecordToast = builder.define("displayRecordToast", true);
		this.silentMusicToasts = builder.define("silentMusicToasts", true);
		this.minSongDelay = builder.defineInRange("minSongDelay", 0, -1, Integer.MAX_VALUE);
		this.maxSongDelay = builder.defineInRange("maxSongDelay", 0, -1, Integer.MAX_VALUE);
	}
}
