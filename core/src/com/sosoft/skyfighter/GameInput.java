package com.sosoft.skyfighter;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.math.Vector2;
//
//public class GameInput {
//
//    public static Vector2 oldMousePos = new Vector2(0,0);
//    public static Vector2 MouseForce = new Vector2(0,0);
//    public static Vector2 KeyForce = new Vector2();
//
//    public static boolean horizon;
//    public static void Update() {
//        KeyForce.x = 0;
//        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
//            horizon = true;
//            KeyForce.x -= 1;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
//            horizon = false;
//            KeyForce.x += 1;
//        }
//
//        KeyForce.y = 0;
//        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
//            KeyForce.y += 1;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
//            KeyForce.y -= 1;
//        }
//    }
//}

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class GameInput implements InputProcessor {
    public static Vector2 oldMousePos = new Vector2(0, 0);

    private boolean mouseHasMoved = false;

    public static Vector2 KeyForce = new Vector2(0, 0);
    public static Vector2 MouseForce = new Vector2(0, 0);

    public GameInput() {
        oldMousePos.x = Gdx.input.getX();
        oldMousePos.y = Gdx.input.getY();
    }

//    private static void updateKeyForce() {
//        KeyForce.x = 0;
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            KeyForce.x -= 1;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            KeyForce.x += 1;
//        }
//
//        KeyForce.y = 0;
//        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            KeyForce.y -= 1;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            KeyForce.y += 1;
//        }
//    }

//    public static void staticUpdate() {
//        updateKeyForce();
//    }

    public void update() {
        if (mouseHasMoved) {
            MouseForce.setZero();
        }
        mouseHasMoved = false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        MouseForce = new Vector2(screenX, screenY).sub(oldMousePos);
        //MouseForce.y *= -1;
        oldMousePos.set(screenX, screenY);
        mouseHasMoved = true;
        Gdx.app.log("Cord: ", "X: " + oldMousePos.x + " Y: " + oldMousePos.y);
        return true;
    }

    public static Vector2 getMousePos() {
        return oldMousePos;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}