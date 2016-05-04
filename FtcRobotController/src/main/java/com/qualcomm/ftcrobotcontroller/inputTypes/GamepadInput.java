package com.qualcomm.ftcrobotcontroller.inputTypes;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadInput {
    private final Gamepad gamepad;

    private float x;
    private float y;

    public GamepadInput(Gamepad gamepad){
        this.gamepad=gamepad;
    }

    public void refreshInput(){
    }
}