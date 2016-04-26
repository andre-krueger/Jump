package com.akrger.jump.screens;

import com.akrger.jump.Jump;
import com.akrger.jump.actors.Jumper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
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
    private World world;

    public GameScreen(final Jump jump) {
        this.jump = jump;
        this.world = new World(new Vector2(0, -98f), true);
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(Jump.WIDTH, Jump.HEIGHT, camera);
        this.stage = new Stage(this.viewport);
        this.jumper = new Jumper(this.world);
        this.stage.addActor(this.jumper);
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
            jumper.addAction(Actions.moveBy(2f, 0));
            Gdx.app.log("Position", String.format("%s", jumper.getX()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            jumper.addAction(Actions.moveBy(-2f, 0));
            Gdx.app.log("Position", String.format("%s", jumper.getX()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            jumper.addAction(Actions.moveBy(0, 2f));
            Gdx.app.log("Position", String.format("%s", jumper.getY()));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        update(delta);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        jumper.setPosition(jumper.body.getPosition().x, jumper.body.getPosition().y);
        stage.getBatch().setProjectionMatrix(camera.combined); // is this needed?
        stage.act(delta);
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
        world.dispose();
    }
}
