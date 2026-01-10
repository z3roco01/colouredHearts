package z3roco01.colouredhearts.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import z3roco01.colouredhearts.ColouredHearts;

@Mixin(InGameHud.HeartType.class)
public abstract class HeartTypeMixin {
    @Unique
    private static final String DARK_GREEN_ROOT = "hud/hearts/dark_green/";
    @Unique
    private static final String GREEN_ROOT = "hud/hearts/green/";
    @Unique
    private static final String YELLOW_ROOT = "hud/hearts/yellow/";
    @Unique
    private static final String RED_ROOT = "hud/hearts/red/";
    @Unique
    private static final String GREY_ROOT = "hud/hearts/grey/";

    /*@Unique
    private static final Identifier DARK_GREEN_FULL = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/dark_green/full");
    @Unique
    private static final Identifier DARK_GREEN_HALF = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/dark_green/half");
    @Unique
    private static final Identifier DARK_GREEN_FULL_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/dark_green/full_blinking");
    @Unique
    private static final Identifier DARK_GREEN_HALF_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/dark_green/half_blinking");

    @Unique
    private static final Identifier GREEN_FULL = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/green/full");
    @Unique
    private static final Identifier GREEN_HALF = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/green/half");
    @Unique
    private static final Identifier GREEN_FULL_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/green/full_blinking");
    @Unique
    private static final Identifier GREEN_HALF_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/green/half_blinking");

    @Unique
    private static final Identifier YELLOW_FULL = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/yellow/full");
    @Unique
    private static final Identifier YELLOW_HALF = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/yellow/half");
    @Unique
    private static final Identifier YELLOW_FULL_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/yellow/full_blinking");
    @Unique
    private static final Identifier YELLOW_HALF_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/yellow/half_blinking");

    @Unique
    private static final Identifier RED_FULL = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/red/full");
    @Unique
    private static final Identifier RED_HALF = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/red/half");
    @Unique
    private static final Identifier RED_FULL_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/red/full_blinking");
    @Unique
    private static final Identifier RED_HALF_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/red/half_blinking");

    @Unique
    private static final Identifier GREY_FULL = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/grey/full");
    @Unique
    private static final Identifier GREY_HALF = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/grey/half");
    @Unique
    private static final Identifier GREY_FULL_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/grey/full_blinking");
    @Unique
    private static final Identifier GREY_HALF_BLINKING = Identifier.of(ColouredHearts.MOD_ID, "hud/hearts/grey/half_blinking");*/

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    private void getTexture(boolean hardcore, boolean half, boolean blinking, CallbackInfoReturnable<Identifier> cir) {
        // dont worry about hardcore hearts, would be odd to use hardcore and life series
        if(hardcore) return;

        // also dont override things like wither and poison hearts
        if(((InGameHud.HeartType)(Object)this) != InGameHud.HeartType.NORMAL) return;

        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        Team team = player.getScoreboardTeam();
        if(team == null) return;

        String idStr = null;

        switch(team.getColor()) {
            /*case BLACK -> idStr = BLACK_ROOT;
            case DARK_BLUE -> idStr = DARK_BLUE_ROOT;
            */
            case DARK_GREEN -> idStr = DARK_GREEN_ROOT;
            /*case DARK_AQUA -> idStr = DARK_AQUA_ROOT;
            case DARK_RED -> idStr = DARK_RED_ROOT;
            case DARK_PURPLE -> idStr = DARK_PURPLE_ROOT;
            case GOLD -> idStr = GOLD_ROOT;
            */
            case GRAY -> idStr = GREY_ROOT;
            /*case DARK_GRAY -> idStr = DARK_GREY_ROOT;
            case BLUE -> idStr = BLUE_ROOT;
            */
            case GREEN -> idStr = GREEN_ROOT;
            //case AQUA -> idStr = AQUA_ROOT;
            case RED -> idStr = RED_ROOT;
            //case LIGHT_PURPLE -> idStr = LIGHT_PURPLE_ROOT;
            case YELLOW -> idStr = YELLOW_ROOT;
            //case WHITE -> idStr = WHITE_ROOT;
        }

        // either an unsupported colour, or smth else, either way cant continue
        if(idStr == null) return;

        if(half) idStr += "half";
        else idStr += "full";

        if(blinking) idStr += "_blinking";

        cir.setReturnValue(Identifier.of(ColouredHearts.MOD_ID, idStr));
    }
}
