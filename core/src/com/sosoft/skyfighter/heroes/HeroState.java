package com.sosoft.skyfighter.heroes;

public class HeroState {
    // STATIC HERO CHARACTERISTICS
    public float maxSpeed;
    public int maxHealth;
    public int maxAirJumps;
    public float jumpHeight;
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
    public boolean airJump;
    public boolean left;
    public boolean right;
    public boolean duck;
    public boolean fire;
    public boolean reload;
    public boolean nextWeapon;
    public boolean prevWeapon;

    // OTHER
    public int health;
    public int kills;
    public int deaths;
    public int streak;
    public boolean dead;
    boolean grounded;
    public int airJumpsRemain;
    boolean lookLeft;
}
