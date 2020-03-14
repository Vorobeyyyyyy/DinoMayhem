package com.sosoft.skyfighter.heroes;

public class HeroState {
    // STATIC HERO CHARACTERISTICS
    public float maxSpeed;
    public int maxHealth;
    public float jumpHeight;
    //

    public float aimAngle;
    public float respawnTime;


    public int health;
    public boolean dead;
    boolean grounded;
    // Input states
    public boolean jump;
    public boolean left;
    public boolean right;
    public boolean duck;
    public boolean firstAbility;
    public boolean secondAbility;
    public boolean thirdAbility;

    public int score;
    public int lives;
}
