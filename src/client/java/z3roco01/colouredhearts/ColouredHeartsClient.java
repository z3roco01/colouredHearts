package z3roco01.colouredhearts;

import net.fabricmc.api.ClientModInitializer;

public class ColouredHeartsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ColouredHearts.LOGGER.info("who up colouring they heart");
	}
}