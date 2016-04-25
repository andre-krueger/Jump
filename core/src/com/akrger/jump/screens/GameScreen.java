package com.akrger.jump.screens;

import com.akrger.jump.Jump;
import com.akrger.jump.actors.Jumper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    private final Jump jump;
    private final Stage stage;
    private Jumper jumper;
    private OrthographicCamera camera;
    private Viewport viewport;

    public GameScreen(final Jump jump) {
        this.jump = jump;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(Jump.WIDTH, Jump.HEIGHT, camera);
        this.stage = new Stage(this.viewport);
        this.jumper = new Jumper();
        this.stage.addActor(jumper);
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void update(final float delta) {
        handleInput(delta);
    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            Gdx.app.log("Move", "");
            jumper.addAction(Actions.moveBy(4f, 0));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        update(delta);
        stage.getBatch().setProjectionMatrix(camera.combined); // is this needed?
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
