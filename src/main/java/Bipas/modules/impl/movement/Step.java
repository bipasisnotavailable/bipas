package Bipas.modules.impl.movement;

import Bipas.event.Event;
import Bipas.event.impl.EventUpdate;
import Bipas.modules.Module;
import Bipas.utilities.value.values.NumberValue;

/**
 * @author avox | lmao | kroko
 * @created on 10.09.2020 : 08:12
 */
public class Step extends Module {

    public NumberValue<Integer> stepHeight = new NumberValue<>("Step Height", 0, 10, 0, this);

    public Step() {
        super("Step", "you step high", Category.MOVEMENT);
        addValue(stepHeight);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            mc.thePlayer.stepHeight = stepHeight.getDefaultValue() == 0 ? 0.6F : stepHeight.getDefaultValue();
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        mc.thePlayer.stepHeight = 0.6F;
    }

}