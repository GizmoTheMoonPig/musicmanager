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
		this.add("keybind.musicmanager.open", "Open Music Manager Screen");
		this.add("keybind.musicmanager.show_toast", "Show Last Played Track Again");

		this.add("gui.musicmanager.music_manager", "Music Manager");
		this.add("gui.musicmanager.display_record_toast", "Display Toasts for Records");
		this.add("gui.musicmanager.display_record_toast.desc", "Determines whether a toast will appear when a music disc starts playing, similar to how music tracks display one with this mod. This option also removes the text appearing above your hotbar when a disc starts.");
		this.add("gui.musicmanager.play_toast_sound", "Play Toast Sounds");
		this.add("gui.musicmanager.play_toast_sound.desc", "Determines if Toasts from this mod will play the 'wooosh' sound effect when they appear and disappear.");
		this.add("gui.musicmanager.min_song_delay", "Minimum Song Delay");
		this.add("gui.musicmanager.min_song_delay.desc", "Sets the minimum amount of time (in seconds) it will take for a new music track to play after the previous one finishes.\nPlease note that setting it to 0 may cause issues with the toasts showing up properly!\nSetting this to -1 will use the default values that vanilla/mods set for their background music.");
		this.add("gui.musicmanager.max_song_delay", "Maximum Song Delay");
		this.add("gui.musicmanager.max_song_delay.desc", "Sets the maximum amount of time (in seconds) it will take for a new music track to play after the previous one finishes.\nPlease note that setting it to 0 may cause issues with the toasts showing up properly!\nSetting this to -1 will use the default values that vanilla/mods set for their background music.");
		this.add("gui.musicmanager.save", "Save");
		this.add("gui.musicmanager.exit", "Exit");

		this.add("sounds.musicmanager.now_playing", "Now Playing: ");

		this.add("sounds.musicmanager.minecraft.music.menu.menu1", "C418 - Mutation");
		this.add("sounds.musicmanager.minecraft.music.menu.menu2", "C418 - Moog City 2");
		this.add("sounds.musicmanager.minecraft.music.menu.menu3", "C418 - Beginning 2");
		this.add("sounds.musicmanager.minecraft.music.menu.menu4", "C418 - Floating Trees");

		this.add("sounds.musicmanager.minecraft.music.game.calm1", "C418 - Minecraft");
		this.add("sounds.musicmanager.minecraft.music.game.calm2", "C418 - Clark");
		this.add("sounds.musicmanager.minecraft.music.game.calm3", "C418 - Sweden");

		this.add("sounds.musicmanager.minecraft.music.game.creative.creative1", "C418 - Biome Fest");
		this.add("sounds.musicmanager.minecraft.music.game.creative.creative2", "C418 - Blind Spots");
		this.add("sounds.musicmanager.minecraft.music.game.creative.creative3", "C418 - Haunt Muskie");
		this.add("sounds.musicmanager.minecraft.music.game.creative.creative4", "C418 - Aria Math");
		this.add("sounds.musicmanager.minecraft.music.game.creative.creative5", "C418 - Dreiton");
		this.add("sounds.musicmanager.minecraft.music.game.creative.creative6", "C418 - Taswell");

		this.add("sounds.musicmanager.minecraft.music.game.hal1", "C418 - Subwoofer Lullaby");
		this.add("sounds.musicmanager.minecraft.music.game.hal2", "C418 - Living Mice");
		this.add("sounds.musicmanager.minecraft.music.game.hal3", "C418 - Haggstrom");
		this.add("sounds.musicmanager.minecraft.music.game.hal4", "C418 - Danny");

		this.add("sounds.musicmanager.minecraft.music.game.nuance1", "C418 - Key");
		this.add("sounds.musicmanager.minecraft.music.game.nuance2", "C418 - Oxygène");

		this.add("sounds.musicmanager.minecraft.music.game.piano1", "C418 - Dry Hands");
		this.add("sounds.musicmanager.minecraft.music.game.piano2", "C418 - Wet Hands");
		this.add("sounds.musicmanager.minecraft.music.game.piano3", "C418 - Mice on Venus");

		this.add("sounds.musicmanager.minecraft.music.game.water.axolotl", "C418 - Axolotl");
		this.add("sounds.musicmanager.minecraft.music.game.water.dragon_fish", "C418 - Dragon Fish");
		this.add("sounds.musicmanager.minecraft.music.game.water.shuniji", "C418 - Shuniji");

		this.add("sounds.musicmanager.minecraft.music.game.nether.nether1", "C418 - Concrete Halls");
		this.add("sounds.musicmanager.minecraft.music.game.nether.nether2", "C418 - Dead Voxel");
		this.add("sounds.musicmanager.minecraft.music.game.nether.nether3", "C418 - Warmth");
		this.add("sounds.musicmanager.minecraft.music.game.nether.nether4", "C418 - Ballad of the Cats");

		this.add("sounds.musicmanager.minecraft.music.game.end.credits", "C418 - Alpha");
		this.add("sounds.musicmanager.minecraft.music.game.end.boss", "C418 - Boss");
		this.add("sounds.musicmanager.minecraft.music.game.end.end", "C418 - The End");

		this.add("sounds.musicmanager.minecraft.music.game.nether.soulsand_valley.so_below", "Lena Raine - So Below");
		this.add("sounds.musicmanager.minecraft.music.game.nether.nether_wastes.rubedo", "Lena Raine - Rubedo");
		this.add("sounds.musicmanager.minecraft.music.game.nether.crimson_forest.chrysopoeia", "Lena Raine - Chrysopoeia");

		this.add("sounds.musicmanager.minecraft.music.game.an_ordinary_day", "Kumi Tanioka - An Ordinary Day");
		this.add("sounds.musicmanager.minecraft.music.game.comforting_memories", "Kumi Tanioka - Comforting Memories");
		this.add("sounds.musicmanager.minecraft.music.game.floating_dream", "Kumi Tanioka - Floating Dream");
		this.add("sounds.musicmanager.minecraft.music.game.infinite_amethyst", "Lena Raine - Infinite Amethyst");
		this.add("sounds.musicmanager.minecraft.music.game.left_to_bloom", "Lena Raine - Left to Bloom");
		this.add("sounds.musicmanager.minecraft.music.game.one_more_day", "Lena Raine - One More Day");
		this.add("sounds.musicmanager.minecraft.music.game.stand_tall", "Lena Raine - Stand Tall");
		this.add("sounds.musicmanager.minecraft.music.game.wending", "Lena Raine - Wending");

		this.add("sounds.musicmanager.minecraft.music.game.ancestry", "Lena Raine - Ancestry");
		this.add("sounds.musicmanager.minecraft.music.game.swamp.labyrinthine", "Lena Raine - Labyrinthine");
		this.add("sounds.musicmanager.minecraft.music.game.swamp.aerie", "Lena Raine - Aerie");
		this.add("sounds.musicmanager.minecraft.music.game.swamp.firebugs", "Lena Raine - Firebugs");

		this.add("sounds.musicmanager.minecraft.music.game.a_familiar_room", "Aaron Cherof - A Familiar Room");
		this.add("sounds.musicmanager.minecraft.music.game.bromeliad", "Aaron Cherof - Bromeliad");
		this.add("sounds.musicmanager.minecraft.music.game.crescent_dunes", "Aaron Cherof - Crescent Dunes");
		this.add("sounds.musicmanager.minecraft.music.game.echo_in_the_wind", "Aaron Cherof - Echo in the Wind");

		// Twilight Forest
		this.add("sounds.musicmanager.twilightforest.music.findings", "MrCompost - Findings");
		this.add("sounds.musicmanager.twilightforest.music.home", "MrCompost - Home");
		this.add("sounds.musicmanager.twilightforest.music.maker", "MrCompost - Maker");
		this.add("sounds.musicmanager.twilightforest.music.motion", "MrCompost - Motion");
		this.add("sounds.musicmanager.twilightforest.music.radiance", "Rotch Gwylt - Radiance");
		this.add("sounds.musicmanager.twilightforest.music.steps", "Rotch Gwylt - Steps");
		this.add("sounds.musicmanager.twilightforest.music.superstitious", "Rotch Gwylt - Superstitious");
		this.add("sounds.musicmanager.twilightforest.music.thread", "MrCompost - Thread");
		this.add("sounds.musicmanager.twilightforest.music.wayfarer", "MrCompost - Wayfarer");

		// The Undergarden
		this.add("sounds.musicmanager.undergarden.music.acasta_gneiss", "ScreemBob - Acasta Gneiss");
		this.add("sounds.musicmanager.undergarden.music.all_that_wiggles_is_wood", "ScreemBob - All that Wiggles is Wood");
		this.add("sounds.musicmanager.undergarden.music.brotherhood", "ScreemBob - Brotherhood");
		this.add("sounds.musicmanager.undergarden.music.caps", "ScreemBob - Caps");
		this.add("sounds.musicmanager.undergarden.music.grongletune", "ScreemBob - Grongletune");
		this.add("sounds.musicmanager.undergarden.music.monument", "ScreemBob - Monument");
		this.add("sounds.musicmanager.undergarden.music.pit_of_shivers", "ScreemBob - Pit of Shivers");
		this.add("sounds.musicmanager.undergarden.music.smog", "ScreemBob - Smog");
		this.add("sounds.musicmanager.undergarden.music.wrought", "ScreemBob - Wrought");

		// Alex's Caves
		this.add("sounds.musicmanager.alexscaves.music.alloy_allure", "LudoCrypt - Alloy Allure");
		this.add("sounds.musicmanager.alexscaves.music.arid_reverie", "Gatetoh - Arid Reverie");
		this.add("sounds.musicmanager.alexscaves.music.dulcet", "Gatetoh - Dulcet");
		this.add("sounds.musicmanager.alexscaves.music.forgotten_tides", "Gatetoh - Forgotten Tides");
		this.add("sounds.musicmanager.alexscaves.music.lux", "Gatetoh - Lux");
		this.add("sounds.musicmanager.alexscaves.music.paleoverdant", "Gatetoh - Paleoverdant");
		this.add("sounds.musicmanager.alexscaves.music.pristine_dawnrise", "Gatetoh - Pristine Dawnrise");
		this.add("sounds.musicmanager.alexscaves.music.ray", "RenRen - Ray");

		// Biomes O' Plenty
		this.add("sounds.musicmanager.biomesoplenty.music.game.nether.blot", "LudoCrypt - Blot");
		this.add("sounds.musicmanager.biomesoplenty.music.game.nether.cortisol", "LudoCrypt - Cortisol");
		this.add("sounds.musicmanager.biomesoplenty.music.game.nether.mesh", "LudoCrypt - Mesh");
		this.add("sounds.musicmanager.biomesoplenty.music.game.nether.redhead", "LudoCrypt - Redhead");
		this.add("sounds.musicmanager.biomesoplenty.music.game.nether.timber", "LudoCrypt - Timber");

		// Blue Skies
		this.add("sounds.musicmanager.blue_skies.music.baneful", "Lachney - Baneful");
		this.add("sounds.musicmanager.blue_skies.music.brightlands", "Lachney - Brightlands");
		this.add("sounds.musicmanager.blue_skies.music.brisegel", "Lachney - Brisegel");
		this.add("sounds.musicmanager.blue_skies.music.brittlebush", "Lachney - Brittlebush");
		this.add("sounds.musicmanager.blue_skies.music.crystal_dunes", "Lachney - Crystal Dunes");
		this.add("sounds.musicmanager.blue_skies.music.gatekeepers_tale", "Lachney - Gatekeeper's Tale");
		this.add("sounds.musicmanager.blue_skies.music.mars", "Lachney - Everdawn");
		this.add("sounds.musicmanager.blue_skies.music.moonlit_bloom", "Lachney - Moonlit Bloom");
		this.add("sounds.musicmanager.blue_skies.music.snowcap", "Lachney - Snowcap");
		this.add("sounds.musicmanager.blue_skies.music.turquoise", "Lachney - Turquoise");
		this.add("sounds.musicmanager.blue_skies.music.whistleshell", "Lachney - Whistleshell");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.blinding_dungeon_ambience", "Lachney - Blinding Tower");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.boss.blinding_boss", "Lachney - Blinding Jam");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.boss.blinding_fanfare", "Lachney - Boss Fanfare");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.boss.generic_boss", "Lachney - Generic Boss");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.boss.generic_fanfare", "Lachney - Boss Fanfare");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.nature_dungeon_ambience", "Lachney - Starlit Passageways");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.boss.nature_boss", "Lachney - Nature Jive");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.boss.nature_fanfare", "Lachney - Boss Fanfare");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.poison_dungeon_ambience", "Lachney - Venomous Tunnels");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.boss.poison_boss", "Lachney - Poison Dance");
		this.add("sounds.musicmanager.blue_skies.music.dungeon.boss.poison_fanfare", "Lachney - Boss Fanfare");

		// The Aether
		this.add("sounds.musicmanager.aether.music.aether1", "Emile Van Krieken - Welcome to Paradise");
		this.add("sounds.musicmanager.aether.music.aether2", "Emile Van Krieken - Wings");
		this.add("sounds.musicmanager.aether.music.aether3", "Emile Van Krieken - Meadow");
		this.add("sounds.musicmanager.aether.music.aether4", "Emile Van Krieken - Moa's Song");
		this.add("sounds.musicmanager.aether.music.aether5", "Emile Van Krieken - Clouds");
		this.add("sounds.musicmanager.aether.music.menu", "Emile Van Krieken - Float");
		this.add("sounds.musicmanager.aether.music.boss.slider_fight", "Emile Van Krieken - Labyrinth's Vengeance");
		this.add("sounds.musicmanager.aether.music.boss.sun_spirit_fight", "RenRen - Ardor");
		this.add("sounds.musicmanager.aether.music.boss.valkyrie_queen_fight", "Sunsette - Trial by Combat");

		// The Bumblezone
		this.add("sounds.musicmanager.the_bumblezone.music.flight_of_the_bumblebee_rimsky_korsakov", "Rimsky Korsakov - Flight of the Bumblebee");
		this.add("sounds.musicmanager.the_bumblezone.music.honey_bee_rat_faced_boy", "Rat Faced Boy - Honey Bee");
		this.add("sounds.musicmanager.the_bumblezone.music.la_bee_da_loca", "LudoCrypt - La Bee-da Loca");
		this.add("sounds.musicmanager.the_bumblezone.music.bee_laxing_with_the_hom_bees", "LudoCrypt - Bee-laxing with the Hom-bees");
		this.add("sounds.musicmanager.the_bumblezone.music.bee_ware_of_the_temple_stereo", "LudoCrypt - Bee-ware of the Temple");
		this.add("sounds.musicmanager.the_bumblezone.music.knowing_renren_stereo", "RenRen - Knowing");
		this.add("sounds.musicmanager.the_bumblezone.music.radiance_renren_stereo", "RenRen - Radiance");
		this.add("sounds.musicmanager.the_bumblezone.music.life_renren_stereo", "RenRen - Life");
		this.add("sounds.musicmanager.the_bumblezone.music.beenna_box_stereo", "Punpudle - Beenna Box");
		this.add("sounds.musicmanager.the_bumblezone.music.drowning_in_despair_stereo", "Punpudle - Drowning in Despair");
		this.add("sounds.musicmanager.the_bumblezone.music.a_last_first_last_stereo", "Punpudle - A Last First Last");

		// The Betweenlands
		this.add("sounds.musicmanager.thebetweenlands.boss.barrishee_theme", "Rotch Gwylt - Barrishee");
		this.add("sounds.musicmanager.thebetweenlands.boss.dreadful_peat_mummy_loop", "Rotch Gwylt - Dreadful Peat Mummy");
		this.add("sounds.musicmanager.thebetweenlands.boss.fortress_boss_loop", "Rotch Gwylt - Primordial Malevolence");
		this.add("sounds.musicmanager.thebetweenlands.boss.pit_of_decay_loop", "Rotch Gwylt - Pit of Decay");
		this.add("sounds.musicmanager.thebetweenlands.music.barrow_mounds", "Scarecrowman - Barrow Mounds");
		this.add("sounds.musicmanager.thebetweenlands.music.crocodile_tears", "Voog2 - Crocodile Tears");
		this.add("sounds.musicmanager.thebetweenlands.music.dont_follow_the_whisps", "Voog2 - Don't Follow the Whisps");
		this.add("sounds.musicmanager.thebetweenlands.music.emerald_embers", "Scarecrowman - Emerald Embers");
		this.add("sounds.musicmanager.thebetweenlands.music.enter_the_mire", "Scarecrowman - Enter the Mire");
		this.add("sounds.musicmanager.thebetweenlands.music.ghostfaces", "Scarecrowman - Ghostfaces");
		this.add("sounds.musicmanager.thebetweenlands.music.in_between", "Voog2 - In Between");
		this.add("sounds.musicmanager.thebetweenlands.music.incantation", "Scarecrowman - Incantation");
		this.add("sounds.musicmanager.thebetweenlands.music.leech_love_part_1", "Voog2 - Leech Love - Part 1");
		this.add("sounds.musicmanager.thebetweenlands.music.leech_love_part_2", "Voog2 - Leech Love - Part 2");
		this.add("sounds.musicmanager.thebetweenlands.music.murk_beneath_a_twinkling_void", "Scarecrowman - Murk Beneath a Twinkling Void");
		this.add("sounds.musicmanager.thebetweenlands.music.numbskull", "Scarecrowman - Numbskull");
		this.add("sounds.musicmanager.thebetweenlands.music.rip_in_the_fold", "Voog2 - Rip in the Fold");
		this.add("sounds.musicmanager.thebetweenlands.music.spore_ballad", "Voog2 - Spore Ballad");
		this.add("sounds.musicmanager.thebetweenlands.music.the_other_world", "Voog2 - The Other World");
		this.add("sounds.musicmanager.thebetweenlands.music.the_quietus", "Scarecrowman - The Quietus");
		this.add("sounds.musicmanager.thebetweenlands.menu.a_foreboding_welcome", "Rotch Gwylt - A Foreboding Welcome");
		this.add("sounds.musicmanager.thebetweenlands.menu.the_adventure_begins", "Rotch Gwylt - The Adventure Begins");
		this.add("sounds.musicmanager.thebetweenlands.menu.this_is_where_it_starts", "Rotch Gwylt - This Is Where It Starts");

		// The Betweenlands - Eternal Melodies
		this.add("sounds.musicmanager.thebetweenlandsmusic.a_walk_through_the_marsh", "Rotch Gwylt - A Walk Through the Marsh");
		this.add("sounds.musicmanager.thebetweenlandsmusic.back_home", "Rotch Gwylt - Back Home");
		this.add("sounds.musicmanager.thebetweenlandsmusic.chiromaw_matriarch", "Rotch Gwylt - Chiromaw Matriarch");
		this.add("sounds.musicmanager.thebetweenlandsmusic.emberling_shamans", "Rotch Gwylt - Emberling Shamans");
		this.add("sounds.musicmanager.thebetweenlandsmusic.eternal", "Rotch Gwylt - Eternal");
		this.add("sounds.musicmanager.thebetweenlandsmusic.labyrinthine_vaults", "Rotch Gwylt - Labyrinthine Vaults");
		this.add("sounds.musicmanager.thebetweenlandsmusic.light_will_follow", "Rotch Gwylt - Light Will Follow");
		this.add("sounds.musicmanager.thebetweenlandsmusic.rowing_through_the_fog", "Rotch Gwylt - Rowing Through The Fog");
		this.add("sounds.musicmanager.thebetweenlandsmusic.spirit_tree", "Rotch Gwylt - Spirit Tree");
		this.add("sounds.musicmanager.thebetweenlandsmusic.strange_but_pleasant", "Rotch Gwylt - Strange but Pleasant");
		this.add("sounds.musicmanager.thebetweenlandsmusic.we_are_the_ones_who_crawl", "Rotch Gwylt - We Are the Ones Who Crawl");
		this.add("sounds.musicmanager.thebetweenlandsmusic.wight_fortress", "Rotch Gwylt - Wight Fortress");
	}
}
