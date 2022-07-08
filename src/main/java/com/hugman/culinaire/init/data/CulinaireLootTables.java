package com.hugman.culinaire.init.data;

import com.hugman.culinaire.init.FoodBundle;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class CulinaireLootTables {
	private static final Identifier ZOMBIE_ENTITY = new Identifier("minecraft", "entities/zombie");
	private static final Identifier ZOMBIE_VILLAGER_ENTITY = new Identifier("minecraft", "entities/zombie_villager");
	private static final Identifier HUSK_ENTITY = new Identifier("minecraft", "entities/husk");

	public static void addToVanillaTables() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			// Lettuce Seeds
			if(LootTables.SIMPLE_DUNGEON_CHEST.equals(id) || LootTables.ABANDONED_MINESHAFT_CHEST.equals(id) || LootTables.WOODLAND_MANSION_CHEST.equals(id)) {
				LootPool.Builder pool = LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
						.conditionally(RandomChanceLootCondition.builder(0.3F))
						.with(ItemEntry.builder(FoodBundle.LETTUCE_SEEDS).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))));
				tableBuilder.pool(pool);
			}

			// Tomato
			if(LootTables.PILLAGER_OUTPOST_CHEST.equals(id)) {
				LootPool.Builder pool = LootPool.builder()
						.rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
						.conditionally(RandomChanceLootCondition.builder(0.45F))
						.with(ItemEntry.builder(FoodBundle.TOMATO).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))));
				tableBuilder.pool(pool);
			}
			if(LootTables.SHIPWRECK_SUPPLY_CHEST.equals(id)) {
				LootPool.Builder pool = LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
						.conditionally(RandomChanceLootCondition.builder(0.45F))
						.with(ItemEntry.builder(FoodBundle.TOMATO).weight(7).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))));
				tableBuilder.pool(pool);
			}
			if(LootTables.VILLAGE_TAIGA_HOUSE_CHEST.equals(id) || LootTables.VILLAGE_SNOWY_HOUSE_CHEST.equals(id) || LootTables.VILLAGE_PLAINS_CHEST.equals(id)) {
				LootPool.Builder pool = LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
						.conditionally(RandomChanceLootCondition.builder(0.25F))
						.with(ItemEntry.builder(FoodBundle.TOMATO).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))));
				tableBuilder.pool(pool);
			}
			if(ZOMBIE_ENTITY.equals(id) || ZOMBIE_VILLAGER_ENTITY.equals(id) || HUSK_ENTITY.equals(id)) {
				LootPool.Builder pool = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.conditionally(RandomChanceWithLootingLootCondition.builder(0.025f, 0.01f))
						.with(ItemEntry.builder(FoodBundle.TOMATO));
				tableBuilder.pool(pool);
			}
		});
	}
}
