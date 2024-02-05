package com.gizmo.music;

import net.neoforged.neoforge.common.ModConfigSpec;

public class MusicConfig {

	public final ModConfigSpec.BooleanValue displayRecordToast;
	public final ModConfigSpec.BooleanValue silentMusicToasts;
	public final ModConfigSpec.IntValue minSongDelay;
	public final ModConfigSpec.IntValue maxSongDelay;

	public MusicConfig(ModConfigSpec.Builder builder) {
		this.displayRecordToast = builder.define("displayRecordToast", true);
		this.silentMusicToasts = builder.define("silentMusicToasts", true);
		this.minSongDelay = builder.defineInRange("minSongDelay", 10, -1, Integer.MAX_VALUE);
		this.maxSongDelay = builder.defineInRange("maxSongDelay", 30, -1, Integer.MAX_VALUE);
	}
}
