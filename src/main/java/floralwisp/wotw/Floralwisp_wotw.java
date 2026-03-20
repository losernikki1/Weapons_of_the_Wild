package floralwisp.wotw;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import net.minecraft.core.registries.Registries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.registry.Registry;

public class Floralwisp_wotw implements ModInitializer {
	public static final String MOD_ID = "floralwisp_wotw";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registerer.initialize();
		ModComponents.initialize();
	}
}