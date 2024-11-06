package entity;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

import animation.Animation;
import animation.SpriteFrame;

import entity.Type;
import move.Move;
import move.Attack;

public class Mokepon {
    
    class Stat {
        double min;
        double max;
        double value;
        int modifier;
    }

    Type[] types = new Type[2];


    public Stat Attack;
    public Stat SpecialAttack;
    public Stat Speed;
    public Stat Defence;
    public Stat SpecialDefence;
    public Stat HealthPoints;

    SpriteFrame[] animation_idle;
    SpriteFrame[] animation_attack;
    SpriteFrame[] animation_damaged;

    
    public Mokepon(Type[] types, Stat Attack,  Stat SpecialAttack, Stat Speed, Stat Defence, Stat SpecialDefence, Stat HealthPoints, SpriteFrame[] animation_idle, SpriteFrame[] animation_attack, SpriteFrame[] animation_damaged) {
        this.Attack = Attack;
        this.SpecialAttack = SpecialAttack;
        this.Speed = Speed;
        this.Defence = Defence;
        this.SpecialDefence = SpecialDefence;
        this.HealthPoints = HealthPoints;

        this.animation_idle = animation_idle;
        this.animation_attack = animation_attack;
        this.animation_damaged = animation_damaged;

        this.types = types;

        setStats();
    }

    private void setStats() {
        Stat[] stats = {this.Attack, this.SpecialAttack, this.Speed, this.Defence, this.SpecialDefence, this.HealthPoints};

        Random ran = new Random();
        for (int index = 0; index < stats.length; index++) {
            double min = stats[index].min;
            double max = stats[index].max;

            stats[index].value = min + (max - min) * ran.nextDouble();
        }
    }

    public boolean modifyStat(Stat stat, int modification) {
        if (stat.modifier + modification > 4 || stat.modifier + modification < -4) {
            return false;
        }
        stat.modifier += modification;
        return true;
    }

    public void damage(Attack Move, double damage) {
        double defence;
        if (Move.PhysicalAttack == true) {
            defence = this.Defence.value;
        } else {
            defence = this.SpecialDefence.value;
        }

        for (int index = 0; index < this.types.length; index++) {
            if (this.types[index] == null) {continue;}
            if (Arrays.stream(this.types[index].immunities).anyMatch(Move.Type::equals)) {

            }

        }

    }

    public void attack(Mokepon target, Move move) {

    }

}