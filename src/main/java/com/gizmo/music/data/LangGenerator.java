package com.gizmo.music.data;

import com.gizmo.music.MusicManager;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class LangGenerator extends LanguageProvider {
	public LangGenerator(PackOutput output) {
		super(output, MusicManager.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("keybind.musicmanager.open", "Open Music Manager Screen");

		add("gui.musicmanager.music_manager", "Music Manager");
		add("gui.musicmanager.display_record_toast", "Display Toasts for Records");
		add("gui.musicmanager.display_record_toast.desc", "Determines whether a toast will appear when a music disc starts playing, similar to how music tracks display one with this mod. This option also removes the text appearing above your hotbar when a disc starts.");
		add("gui.musicmanager.play_toast_sound", "Play Toast Sounds");
		add("gui.musicmanager.play_toast_sound.desc", "Determines if Toasts from this mod will play the 'wooosh' sound effect when they appear and disappear.");
		add("gui.musicmanager.min_song_delay", "Minimum Song Delay");
		add("gui.musicmanager.min_song_delay.desc", "Sets the minimum amount of time (in seconds) it will take for a new music track to play after the previous one finishes.\nPlease note that setting it to 0 may cause issues with the toasts showing up properly!\nSetting this to -1 will use the default values that vanilla/mods set for their background music.");
		add("gui.musicmanager.max_song_delay", "Maximum Song Delay");
		add("gui.musicmanager.max_song_delay.desc", "Sets the maximum amount of time (in seconds) it will take for a new music track to play after the previous one finishes.\nPlease note that setting it to 0 may cause issues with the toasts showing up properly!\nSetting this to -1 will use the default values that vanilla/mods set for their background music.");
		add("gui.musicmanager.save", "Save");
		add("gui.musicmanager.exit", "Exit");
		
		add("sounds.musicmanager.now_playing", "Now Playing: ");

		add("sounds.musicmanager.minecraft.music.menu.menu1", "C418 - Mutation");
		add("sounds.musicmanager.minecraft.music.menu.menu2", "C418 - Moog City 2");
		add("sounds.musicmanager.minecraft.music.menu.menu3", "C418 - Beginning 2");
		add("sounds.musicmanager.minecraft.music.menu.menu4", "C418 - Floating Trees");

		add("sounds.musicmanager.minecraft.music.game.calm1", "C418 - Minecraft");
		add("sounds.musicmanager.minecraft.music.game.calm2", "C418 - Clark");
		add("sounds.musicmanager.minecraft.music.game.calm3", "C418 - Sweden");

		add("sounds.musicmanager.minecraft.music.game.creative.creative1", "C418 - Biome Fest");
		add("sounds.musicmanager.minecraft.music.game.creative.creative2", "C418 - Blind Spots");
		add("sounds.musicmanager.minecraft.music.game.creative.creative3", "C418 - Haunt Muskie");
		add("sounds.musicmanager.minecraft.music.game.creative.creative4", "C418 - Aria Math");
		add("sounds.musicmanager.minecraft.music.game.creative.creative5", "C418 - Dreiton");
		add("sounds.musicmanager.minecraft.music.game.creative.creative6", "C418 - Taswell");

		add("sounds.musicmanager.minecraft.music.game.hal1", "C418 - Subwoofer Lullaby");
		add("sounds.musicmanager.minecraft.music.game.hal2", "C418 - Living Mice");
		add("sounds.musicmanager.minecraft.music.game.hal3", "C418 - Haggstrom");
		add("sounds.musicmanager.minecraft.music.game.hal4", "C418 - Danny");

		add("sounds.musicmanager.minecraft.music.game.nuance1", "C418 - Key");
		add("sounds.musicmanager.minecraft.music.game.nuance2", "C418 - Oxygène");

		add("sounds.musicmanager.minecraft.music.game.piano1", "C418 - Dry Hands");
		add("sounds.musicmanager.minecraft.music.game.piano2", "C418 - Wet Hands");
		add("sounds.musicmanager.minecraft.music.game.piano3", "C418 - Mice on Venus");

		add("sounds.musicmanager.minecraft.music.game.water.shunji", "C418 - Shunji");
		add("sounds.musicmanager.minecraft.music.game.water.axolotl", "C418 - Axolotl");
		add("sounds.musicmanager.minecraft.music.game.water.dragon_fish", "C418 - Dragon Fish");

		add("sounds.musicmanager.minecraft.music.game.nether.nether1", "C418 - Concrete Halls");
		add("sounds.musicmanager.minecraft.music.game.nether.nether2", "C418 - Dead Voxel");
		add("sounds.musicmanager.minecraft.music.game.nether.nether3", "C418 - Warmth");
		add("sounds.musicmanager.minecraft.music.game.nether.nether4", "C418 - Ballad of the Cats");

		add("sounds.musicmanager.minecraft.music.game.end.credits", "C418 - Alpha");
		add("sounds.musicmanager.minecraft.music.game.end.boss", "C418 - Boss");
		add("sounds.musicmanager.minecraft.music.game.end.end", "C418 - The End");

		add("sounds.musicmanager.minecraft.music.game.nether.soulsand_valley.so_below", "Lena Raine - So Below");
		add("sounds.musicmanager.minecraft.music.game.nether.nether_wastes.rubedo", "Lena Raine - Rubedo");
		add("sounds.musicmanager.minecraft.music.game.nether.crimson_forest.chrysopoeia", "Lena Raine - Chrysopoeia");

		add("sounds.musicmanager.minecraft.music.game.an_ordinary_day", "Kumi Tanioka - An Ordinary Day");
		add("sounds.musicmanager.minecraft.music.game.comforting_memories", "Kumi Tanioka - Comforting Memories");
		add("sounds.musicmanager.minecraft.music.game.floating_dream", "Kumi Tanioka - Floating Dream");
		add("sounds.musicmanager.minecraft.music.game.infinite_amethyst", "Lena Raine - Infinite Amethyst");
		add("sounds.musicmanager.minecraft.music.game.left_to_bloom", "Lena Raine - Left to Bloom");
		add("sounds.musicmanager.minecraft.music.game.one_more_day", "Lena Raine - One More Day");
		add("sounds.musicmanager.minecraft.music.game.stand_tall", "Lena Raine - Stand Tall");
		add("sounds.musicmanager.minecraft.music.game.wending", "Lena Raine - Wending");

		add("sounds.musicmanager.minecraft.music.game.ancestry", "Lena Raine - Ancestry");
		add("sounds.musicmanager.minecraft.music.game.labyrinthine", "Lena Raine - Labyrinthine");
		add("sounds.musicmanager.minecraft.music.game.aerie", "Lena Raine - Aerie");
		add("sounds.musicmanager.minecraft.music.game.firebugs", "Lena Raine - Firebugs");

		add("sounds.musicmanager.twilightforest.music.findings", "MrCompost - Findings");
		add("sounds.musicmanager.twilightforest.music.home", "MrCompost - Home");
		add("sounds.musicmanager.twilightforest.music.maker", "MrCompost - Maker");
		add("sounds.musicmanager.twilightforest.music.motion", "MrCompost - Motion");
		add("sounds.musicmanager.twilightforest.music.radiance", "Rotch Gwylt - Radiance");
		add("sounds.musicmanager.twilightforest.music.steps", "Rotch Gwylt - Steps");
		add("sounds.musicmanager.twilightforest.music.superstitious", "Rotch Gwylt - Superstitious");
		add("sounds.musicmanager.twilightforest.music.thread", "MrCompost - Thread");
		add("sounds.musicmanager.twilightforest.music.wayfarer", "MrCompost - Wayfarer");

		add("sounds.musicmanager.undergarden.sounds.music.acasta_gneiss", "ScreemBob - Acasta Gneiss");
		add("sounds.musicmanager.undergarden.sounds.music.all_that_wiggles_is_wood", "ScreemBob - All that Wiggles is Wood");
		add("sounds.musicmanager.undergarden.sounds.music.brotherhood", "ScreemBob - Brotherhood");
		add("sounds.musicmanager.undergarden.sounds.music.caps", "ScreemBob - Caps");
		add("sounds.musicmanager.undergarden.sounds.music.grongletune", "ScreemBob - Grongletune");
		add("sounds.musicmanager.undergarden.sounds.music.monument", "ScreemBob - Monument");
		add("sounds.musicmanager.undergarden.sounds.music.pit_of_shivers", "ScreemBob - Pit of Shivers");
		add("sounds.musicmanager.undergarden.sounds.music.smog", "ScreemBob - Smog");
		add("sounds.musicmanager.undergarden.sounds.music.wrought", "ScreemBob - Wrought");

		add("sounds.musicmanager.blue_skies.music.baneful", "Lachney - Baneful");
		add("sounds.musicmanager.blue_skies.music.brightlands", "Lachney - Brightlands");
		add("sounds.musicmanager.blue_skies.music.brisegel", "Lachney - Brisegel");
		add("sounds.musicmanager.blue_skies.music.brittlebush", "Lachney - Brittlebush");
		add("sounds.musicmanager.blue_skies.music.crystal_dunes", "Lachney - Crystal Dunes");
		add("sounds.musicmanager.blue_skies.music.gatekeepers_tale", "Lachney - Gatekeeper's Tale");
		add("sounds.musicmanager.blue_skies.music.mars", "Lachney - Everdawn");
		add("sounds.musicmanager.blue_skies.music.moonlit_bloom", "Lachney - Moonlit Bloom");
		add("sounds.musicmanager.blue_skies.music.snowcap", "Lachney - Snowcap");
		add("sounds.musicmanager.blue_skies.music.turquoise", "Lachney - Turquoise");
		add("sounds.musicmanager.blue_skies.music.whistleshell", "Lachney - Whistleshell");

		add("sounds.musicmanager.blue_skies.music.dungeon.blinding_dungeon_ambience", "Lachney - Blinding Tower");
		add("sounds.musicmanager.blue_skies.music.dungeon.boss.blinding_boss", "Lachney - Blinding Jam");
		add("sounds.musicmanager.blue_skies.music.dungeon.boss.blinding_fanfare", "Lachney - Boss Fanfare");

		add("sounds.musicmanager.blue_skies.music.dungeon.boss.generic_boss", "Lachney - Generic Boss");
		add("sounds.musicmanager.blue_skies.music.dungeon.boss.generic_fanfare", "Lachney - Boss Fanfare");

		add("sounds.musicmanager.blue_skies.music.dungeon.nature_dungeon_ambience", "Lachney - Starlit Passageways");
		add("sounds.musicmanager.blue_skies.music.dungeon.boss.nature_boss", "Lachney - Nature Jive");
		add("sounds.musicmanager.blue_skies.music.dungeon.boss.nature_fanfare", "Lachney - Boss Fanfare");

		add("sounds.musicmanager.blue_skies.music.dungeon.poison_dungeon_ambience", "Lachney - Venomous Tunnels");
		add("sounds.musicmanager.blue_skies.music.dungeon.boss.poison_boss", "Lachney - Poison Dance");
		add("sounds.musicmanager.blue_skies.music.dungeon.boss.poison_fanfare", "Lachney - Boss Fanfare");

		add("sounds.musicmanager.the_bumblezone.flight_of_the_bumblebee_rimsky_korsakov", "Rimsky Korsakov - Flight of the Bumblebee");
		add("sounds.musicmanager.the_bumblezone.honey_bee_rat_faced_boy", "Rat Faced Boy - Honey Bee");
		add("sounds.musicmanager.the_bumblezone.la_bee_da_loca", "LudoCrypt - La Bee-da Loca");
		add("sounds.musicmanager.the_bumblezone.bee_laxing_with_the_hom_bees", "LudoCrypt - Bee-laxing with the Hom-bees");

		add("sounds.musicmanager.aether.music.aether1", "Emile Van Krieken - Welcome to Paradise");
		add("sounds.musicmanager.aether.music.aether2", "Emile Van Krieken - Wings");
		add("sounds.musicmanager.aether.music.aether3", "Emile Van Krieken - Meadow");
		add("sounds.musicmanager.aether.music.aether4", "Emile Van Krieken - Moa's Song");
		add("sounds.musicmanager.aether.music.menu", "Emile Van Krieken - Float");
	}
}
