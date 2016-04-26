package com.akrger.jump.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/***
 * Could also use Image?
 */
public class Jumper extends Actor {

    private final Texture texture = new Texture(Gdx.files.internal("jumper.png"));

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX(), this.getY());
    }
}
