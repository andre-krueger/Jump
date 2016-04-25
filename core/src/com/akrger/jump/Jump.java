package com.akrger.jump;

import com.akrger.jump.screens.GameScreen;
import com.badlogic.gdx.Game;

public class Jump extends Game {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
