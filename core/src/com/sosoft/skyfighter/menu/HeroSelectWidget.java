package com.sosoft.skyfighter.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.sosoft.skyfighter.AnimatedImage;

public class HeroSelectWidget extends Table {
    public boolean isReady = false;
    final TextField nameField;
    final TextButton readyButton;

    public HeroSelectWidget(String name, Skin skin, int width, Array<JsonValue> animations)
    {
        nameField = new TextField(name, skin);
        readyButton = new TextButton("Ready - No", skin);

        Table heroSelectArea = new Table();
        heroSelectArea.add(new TextButton("<", skin)).width(40);
        heroSelectArea.add(new AnimatedImage(animations.get(1))).size(200);
        heroSelectArea.add(new TextButton(">", skin)).width(40);

        add(nameField).row();
        add(heroSelectArea).row();
        add(readyButton).width(width * 0.9f);
        readyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setReady(!isReady);
                super.clicked(event, x, y);
            }
        });

    }

    public void setReady(boolean ready)
    {
        isReady = ready;
        nameField.setDisabled(isReady);
        if (isReady)
            readyButton.setText("Ready - Yes");
        else
            readyButton.setText("Ready - No");
    }

    public void changeHero(boolean right)
    {
        isReady = !isReady;
        nameField.setDisabled(isReady);
        if (isReady)
            readyButton.setText("Ready - Yes");
        else
            readyButton.setText("Ready - No");
    }

}
