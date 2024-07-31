package com.gizmo.music;

import net.neoforged.neoforge.common.ModConfigSpec;

public class MusicConfig {

	public final ModConfigSpec.BooleanValue displayRecordToast;
	public final ModConfigSpec.BooleanValue silentMusicToasts;
	public final ModConfigSpec.IntValue minSongDelay;
	public final ModConfigSpec.IntValue maxSongDelay;

	public MusicConfig(ModConfigSpec.Builder builder) {
		this.displayRecordToast = builder.translation("gui.musicmanager.display_record_toast").define("displayRecordToast", true);
		this.silentMusicToasts = builder.translation("gui.musicmanager.play_toast_sound").define("silentMusicToasts", true);
		this.minSongDelay = builder.translation("gui.musicmanager.min_song_delay").defineInRange("minSongDelay", 100, -1, Integer.MAX_VALUE);
		this.maxSongDelay = builder.translation("gui.musicmanager.max_song_delay").defineInRange("maxSongDelay", 300, -1, Integer.MAX_VALUE);
	}
}
