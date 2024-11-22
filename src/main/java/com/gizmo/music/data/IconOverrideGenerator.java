package com.gizmo.music.data;

import com.gizmo.music.api.IconOverrideProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class IconOverrideGenerator extends IconOverrideProvider {
	public IconOverrideGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, helper);
	}

	@Override
	protected void createOverrides() {
		this.createDimensionIcon(Level.OVERWORLD.location(), Blocks.GRASS_BLOCK);
		this.createDimensionIcon(Level.NETHER.location(), Blocks.NETHERRACK);
		this.createDimensionIcon(Level.END.location(), Blocks.END_STONE);
		this.createDimensionIcon(ResourceLocation.fromNamespaceAndPath("aether", "the_aether"), ResourceLocation.fromNamespaceAndPath("aether", "aether_portal_frame"));
		this.createDimensionIcon(ResourceLocation.fromNamespaceAndPath("blue_skies", "everbright"), ResourceLocation.fromNamespaceAndPath("blue_skies", "everbright_portal"));
		this.createDimensionIcon(ResourceLocation.fromNamespaceAndPath("blue_skies", "everdawn"), ResourceLocation.fromNamespaceAndPath("blue_skies", "everdawn_portal"));
		this.createDimensionIcon(ResourceLocation.fromNamespaceAndPath("rats", "ratlantis"), ResourceLocation.fromNamespaceAndPath("rats", "chunky_cheese_token"));
		this.createDimensionIcon(ResourceLocation.fromNamespaceAndPath("thebetweenlands", "the_betweenlands"), ResourceLocation.fromNamespaceAndPath("thebetweenlands", "swamp_grass"));
		this.createDimensionIcon(ResourceLocation.fromNamespaceAndPath("the_bumblezone", "the_bumblezone"), ResourceLocation.fromNamespaceAndPath("the_bumblezone", "essence_of_the_bees"));
		this.createDimensionIcon(ResourceLocation.fromNamespaceAndPath("twilightforest", "twilight_forest"), ResourceLocation.fromNamespaceAndPath("twilightforest", "twilight_portal_miniature_structure"));
		this.createDimensionIcon(ResourceLocation.fromNamespaceAndPath("undergarden", "undergarden"), ResourceLocation.fromNamespaceAndPath("undergarden", "deepturf_block"));
		this.createDimensionIcon(ResourceLocation.fromNamespaceAndPath("witherstormmod", "bowels"), ResourceLocation.fromNamespaceAndPath("witherstormmod", "command_block_book"));

		this.createBiomeIcon(Biomes.DEEP_DARK.location(), Blocks.SCULK);
		this.createBiomeIcon(Biomes.CRIMSON_FOREST.location(), Blocks.CRIMSON_NYLIUM);
		this.createBiomeIcon(Biomes.WARPED_FOREST.location(), Blocks.WARPED_NYLIUM);
		this.createBiomeIcon(Biomes.SOUL_SAND_VALLEY.location(), Blocks.SOUL_SOIL);
		this.createBiomeIcon(Biomes.BASALT_DELTAS.location(), Blocks.BASALT);

		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("alexscaves", "forlorn_hollows"), ResourceLocation.fromNamespaceAndPath("alexscaves", "peering_coprolith"));
		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("alexscaves", "abyssal_chasm"), ResourceLocation.fromNamespaceAndPath("alexscaves", "muck"));
		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("alexscaves", "toxic_caves"), ResourceLocation.fromNamespaceAndPath("alexscaves", "radrock"));
		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("alexscaves", "magnetic_caves"), ResourceLocation.fromNamespaceAndPath("alexscaves", "galena"));
		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("alexscaves", "primordial_caves"), ResourceLocation.fromNamespaceAndPath("alexscaves", "limestone"));

		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "erupting_inferno"), ResourceLocation.fromNamespaceAndPath("biomesoplenty", "brimstone"));
		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "visceral_heap"), ResourceLocation.fromNamespaceAndPath("biomesoplenty", "flesh"));
		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "withered_abyss"), Blocks.BLACKSTONE);

		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("thebetweenlands", "sludge_plains"), ResourceLocation.fromNamespaceAndPath("thebetweenlands", "sludgy_dirt"));
		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("thebetweenlands", "sludge_clearing"), ResourceLocation.fromNamespaceAndPath("thebetweenlands", "compacted_mud"));
		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("thebetweenlands", "deep_waters"), ResourceLocation.fromNamespaceAndPath("thebetweenlands", "mossy_cragrock_top"));

		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("undergarden", "frostfields"), ResourceLocation.fromNamespaceAndPath("undergarden", "frozen_deepturf_block"));
		this.createBiomeIcon(ResourceLocation.fromNamespaceAndPath("undergarden", "smog_spires"), ResourceLocation.fromNamespaceAndPath("undergarden", "ashen_deepturf_block"));
	}
}
