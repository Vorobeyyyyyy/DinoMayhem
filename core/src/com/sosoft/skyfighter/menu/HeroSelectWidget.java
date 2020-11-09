package com.sosoft.skyfighter.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.AnimatedImage;
import com.sosoft.skyfighter.content.HeroContent;

public class HeroSelectWidget extends Table {
    public boolean isReady = false;
    final TextField playerNameField;
    final Label heroNameField;
    final TextButton readyButton;
    Array<HeroContent> heroContentArray;
    Array<AnimatedImage> heroImages;
    int currentHero;
    Cell<AnimatedImage> curHeroImage;

    public HeroSelectWidget(String name, Skin skin, int width, Array<HeroContent> heroContentArray)
    {
        currentHero = 0;
        playerNameField = new TextField(name, skin);
        heroNameField = new Label(heroContentArray.get(currentHero).name, skin);
        readyButton = new TextButton("Ready - No", skin);
        TextButton leftButton = new TextButton("<", skin);
        TextButton rightButton = new TextButton(">", skin);
        this.heroContentArray = heroContentArray;
        heroImages = new Array<>();
        for (HeroContent heroContent : heroContentArray){
                heroImages.add(new AnimatedImage(heroContent.runAnimation));
        }

        Table heroSelectArea = new Table();
        heroSelectArea.add(leftButton).width(40);
        curHeroImage = heroSelectArea.add(heroImages.get(currentHero)).size(200);
        heroSelectArea.add(rightButton).width(40);


        add(playerNameField).row();
        add(heroNameField).row();
        add(heroSelectArea).row();
        add(readyButton).width(width * 0.9f);
        readyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setReady(!isReady);
                super.clicked(event, x, y);
            }
        });
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeHero(false);
                super.clicked(event, x, y);
            }
        });
        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeHero(true);
                super.clicked(event, x, y);
            }
        });

    }

    public void setReady(boolean ready)
    {
        isReady = ready;
        playerNameField.setDisabled(isReady);
        if (isReady)
            readyButton.setText("Ready - Yes");
        else
            readyButton.setText("Ready - No");
    }

    public void changeHero(boolean right)
    {
        if (right){
            if (++currentHero >= heroImages.size)
                currentHero = 0;
        }
        else{
            if (--currentHero < 0)
                currentHero = heroImages.size - 1;
        }

        curHeroImage.setActor(heroImages.get(currentHero));
        heroNameField.setText(heroContentArray.get(currentHero).name);
    }

    public String getCurrentHeroName() {
        return heroContentArray.get(currentHero).name;
    }

}
