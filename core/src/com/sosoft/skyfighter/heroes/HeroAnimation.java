package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.Animation;

public class HeroAnimation {

    private String INACTION = "Walk";
    private String RIGHT = "Right";
    private String LEFT = "Left";
    private String JUMP = "Jump";
    private String JUMPDOWN = "JumpDown";
    private String HIT = "Hit";
    private String DIE = "Die";

    public Animation stateAnim;
    public Animation rightAnim;
    public Animation leftAnim;
    public Animation jumpAnim;
    public Animation hitAnim;
    public Animation dieAnim;
    public Animation jumpDownAnim;

    static void setAnimation(Animation animation, float timeAnim, int countFrames, String hero ,String act) {
        Array<Texture> frames = new Array<Texture>();
        for(int i = 0; i < countFrames; i++)
            frames.add(new Texture(hero + act + String.valueOf(i) + ".png"));
        animation = new Animation(timeAnim, countFrames, frames);
    }



}
