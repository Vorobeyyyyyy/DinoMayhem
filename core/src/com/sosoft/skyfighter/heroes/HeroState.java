package com.sosoft.skyfighter.heroes;

public class HeroState {
    // STATIC HERO CHARACTERISTICS
    public float maxSpeed;
    public int maxHealth;
    public float jumpHeight;
    public float firstAbilityCooldown;
    public float secondAbilityCooldown;
    public float thirdAbilityCooldown;

    //COOLDOWNS
    public float respawnTime;
    public float firstAbilityCurrentCooldown;
    public float secondAbilityCurrentCooldown;
    public float thirdAbilityCurrentCooldown;

    // Input states
    public float aimAngle;
    public boolean jump;
    public boolean left;
    public boolean right;
    public boolean duck;
    public boolean firstAbility;
    public boolean secondAbility;
    public boolean thirdAbility;

    // OTHER
    public int health;
    public int score;
    public int lives;
    public boolean dead;
    boolean grounded;
    boolean groundedChanged;
}
