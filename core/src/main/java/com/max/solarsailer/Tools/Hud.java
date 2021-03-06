package com.max.solarsailer.Tools;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.max.solarsailer.Loading.Paths.SkinPaths;
import com.max.solarsailer.Screens.InitialLvlScreen;
import com.max.solarsailer.SolarSailerMain;

public class Hud implements Disposable {
    SolarSailerMain game;
    Stage stage;
    Skin skin;
    public ImageButton gravityOnOffButton;
    Label gravityLabel;
    Label lvlLable;

    public Hud(SolarSailerMain game) {
        this.game = game;
    }

    public void init(Ship ship){
        stage = new Stage();
        skin = game.getAssMan().get(SkinPaths.SGX_SKIN);
        stage.getViewport().setCamera(stage.getCamera());
        Gdx.input.setInputProcessor(stage);
        gravityLabel = new Label("GRAVITY", skin);
        gravityOnOffButton = new ImageButton(skin, "switch");
        gravityLabel.setAlignment(Align.center);
        gravityLabel.setPosition(stage.getViewport().getWorldWidth() - gravityLabel.getWidth(),
                stage.getViewport().getWorldHeight() - gravityLabel.getHeight());
        gravityLabel.setColor(1,1,1,.33f);
        gravityOnOffButton.setPosition(gravityLabel.getX(), gravityLabel.getY() - gravityOnOffButton.getHeight());
        gravityOnOffButton.setColor(gravityOnOffButton.getColor().r, gravityOnOffButton.getColor().g - 1, gravityOnOffButton.getColor().b -1 , .33f);
        gravityOnOffButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(gravityOnOffButton.isChecked()){
                    ship.setGravity(true);
                }else {
                    ship.setGravity(false);
                }
            }
        });
        lvlLable = new Label("Level " + InitialLvlScreen.lvl, skin);
        lvlLable.setPosition(2, stage.getViewport().getWorldHeight() - lvlLable.getHeight() - 2);
        lvlLable.setColor(.9f, .2f, .6f, .5f);

        stage.addActor(gravityLabel);
        stage.addActor(gravityOnOffButton);
        stage.addActor(lvlLable);
    }

    public void update(){
       stage.act();
       stage.draw();
    }

    public void resize(int width, int height){
        //Todo: someone fix this please
        stage.getViewport().update(width, height);
        //stage.getViewport().setWorldSize(width, height);
        /*gravityLabel.setPosition(stage.getViewport().getWorldWidth() - gravityLabel.getWidth(),
                stage.getViewport().getWorldHeight() - gravityLabel.getHeight());*/
        gravityOnOffButton.setPosition(gravityLabel.getX(), gravityLabel.getY() - gravityOnOffButton.getHeight());
    }

    @Override
    public void dispose() {
        if(stage != null) {stage.dispose();}
    }
}
