package com.gizmo.music.data;

import com.gizmo.music.api.IconOverrideProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IconOverrideGenerator extends IconOverrideProvider {
	protected IconOverrideGenerator(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, helper);
	}

	@Override
	protected void createOverrides() {
		this.createDimensionIcon(Level.OVERWORLD.location(), Blocks.GRASS_BLOCK);
		this.createDimensionIcon(Level.NETHER.location(), Blocks.NETHERRACK);
		this.createDimensionIcon(Level.END.location(), Blocks.END_STONE);
		this.createDimensionIcon(new ResourceLocation("aether", "the_aether"), new ResourceLocation("aether", "aether_portal_frame"));
		this.createDimensionIcon(new ResourceLocation("blue_skies", "everbright"), new ResourceLocation("blue_skies", "everbright_portal"));
		this.createDimensionIcon(new ResourceLocation("blue_skies", "everdawn"), new ResourceLocation("blue_skies", "everdawn_portal"));
		this.createDimensionIcon(new ResourceLocation("rats", "ratlantis"), new ResourceLocation("rats", "chunky_cheese_token"));
		this.createDimensionIcon(new ResourceLocation("the_bumblezone", "the_bumblezone"), new ResourceLocation("the_bumblezone", "essence_of_the_bees"));
		this.createDimensionIcon(new ResourceLocation("twilightforest", "twilight_forest"), new ResourceLocation("twilightforest", "twilight_portal_miniature_structure"));
		this.createDimensionIcon(new ResourceLocation("undergarden", "undergarden"), new ResourceLocation("undergarden", "deepturf_block"));
		this.createDimensionIcon(new ResourceLocation("witherstormmod", "bowels"), new ResourceLocation("witherstormmod", "command_block_book"));

		this.createBiomeIcon(Biomes.DEEP_DARK.location(), Blocks.SCULK);
		this.createBiomeIcon(Biomes.CRIMSON_FOREST.location(), Blocks.CRIMSON_NYLIUM);
		this.createBiomeIcon(Biomes.WARPED_FOREST.location(), Blocks.WARPED_NYLIUM);
		this.createBiomeIcon(Biomes.SOUL_SAND_VALLEY.location(), Blocks.SOUL_SOIL);
		this.createBiomeIcon(Biomes.BASALT_DELTAS.location(), Blocks.BASALT);

		this.createBiomeIcon(new ResourceLocation("alexscaves", "forlorn_hollows"), new ResourceLocation("alexscaves", "peering_coprolith"));
		this.createBiomeIcon(new ResourceLocation("alexscaves", "abyssal_chasm"), new ResourceLocation("alexscaves", "muck"));
		this.createBiomeIcon(new ResourceLocation("alexscaves", "toxic_caves"), new ResourceLocation("alexscaves", "radrock"));
		this.createBiomeIcon(new ResourceLocation("alexscaves", "magnetic_caves"), new ResourceLocation("alexscaves", "galena"));
		this.createBiomeIcon(new ResourceLocation("alexscaves", "primordial_caves"), new ResourceLocation("alexscaves", "limestone"));

		this.createBiomeIcon(new ResourceLocation("biomesoplenty", "erupting_inferno"), new ResourceLocation("biomesoplenty", "brimstone"));
		this.createBiomeIcon(new ResourceLocation("biomesoplenty", "visceral_heap"), new ResourceLocation("biomesoplenty", "flesh"));
		this.createBiomeIcon(new ResourceLocation("biomesoplenty", "withered_abyss"), Blocks.BLACKSTONE);

		this.createBiomeIcon(new ResourceLocation("undergarden", "frostfields"), new ResourceLocation("undergarden", "frozen_deepturf_block"));
		this.createBiomeIcon(new ResourceLocation("undergarden", "smog_spires"), new ResourceLocation("undergarden", "ashen_deepturf_block"));

	}
}
