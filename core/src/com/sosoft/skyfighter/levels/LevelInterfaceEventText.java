package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;

import static com.sosoft.skyfighter.levels.Constants.MAXMESSAGECTOUTN;


public class LevelInterfaceEventText {
    private class EventMessage {
        String message;
        Color color;
        float transparency;

        EventMessage(String msg, Color color) {
            this.message = msg;
            this.color = color;
            transparency = 5f;
        }
    }

    BitmapFont bitmapFont;
    Queue<EventMessage> messages = new Queue<EventMessage>();
    SpriteBatch batch;


    LevelInterfaceEventText() {
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Interface/eventfont.otf"));
        FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontParameter();
        freeTypeFontParameter.size = Gdx.graphics.getWidth()/70;
        bitmapFont = freeTypeFontGenerator.generateFont(freeTypeFontParameter);
        freeTypeFontGenerator.dispose();
        batch = new SpriteBatch();
    }

    public void add_message(String msg, Color color) {
        messages.addFirst(new EventMessage(msg, color));
        if (messages.size > MAXMESSAGECTOUTN)
            messages.removeIndex(MAXMESSAGECTOUTN);
    }

    public void update() {
        for (EventMessage eventMessage : messages)
            eventMessage.transparency -= 0.03f;

    }

    public void draw() {
        batch.begin();
        int yOffset = 0;
        for (EventMessage eventMessage : messages) {
            bitmapFont.setColor(eventMessage.color.r,eventMessage.color.g,eventMessage.color.b,eventMessage.transparency);
            bitmapFont.draw(batch, eventMessage.message, 0, Gdx.graphics.getHeight() - bitmapFont.getData().lineHeight * 4 + yOffset, Gdx.graphics.getWidth() - 5, Align.right, false);
            yOffset += bitmapFont.getData().lineHeight;
        }
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        bitmapFont.dispose();
    }
}
