package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Creature {

    private Random rand = new Random();
    private String name;
    MainFrame frame;
    public int x;
    public int y;
    public int health;
    public int damage;
    public boolean alive = true;
    public int MAX_HEALTH;
    public int movePoints;
    public int attackPoints;
    public int range;
    public int armour;
    public int exp;
    public int regenerationHealth;
    public int MAX_MOVE_POINTS;
    public int MAX_ATTACK_POINTS;
    public int MAX_ARMOUR;
    public int MAX_RANGE;
    public int MAX_INCREASING_MOVE_POINTS;
    public int MAX_INCREASING_ATTACK_POINTS;
    public int expPrice;
    public int mana;
    public int regenerationMana;
    public int MAX_MANA;
    public ArrayList<Spell> spells = new ArrayList<Spell>();
    public Spell chosenSpell;
    public ArrayList<Creature> targets;
    public Creature target;
    public Creature allyTarget;

    public Creature(String name, int MAX_HEALTH, int movePoints, int attackPoints, int range, int armour, int damage, int regenerationHealth, int expPrice, ArrayList<Creature> targets){
        this.name = name;
        this.MAX_HEALTH = MAX_HEALTH;
        this.health = this.MAX_HEALTH;
        this.movePoints = movePoints;
        this.expPrice = expPrice;
        this.attackPoints = attackPoints;
        this.MAX_MOVE_POINTS = this.movePoints;
        this.MAX_ATTACK_POINTS = this.attackPoints;
        this.MAX_INCREASING_MOVE_POINTS = 5;
        this.MAX_INCREASING_ATTACK_POINTS = 4;
        this.MAX_RANGE = 4;
        this.MAX_ARMOUR = 16;
        this.range = range;
        this.regenerationHealth = regenerationHealth;
        this.armour = armour;
        this.damage = damage;
        this.targets = targets;
        this.exp = 5;
        this.allyTarget = null;
        this.mana = 2;
        this.MAX_MANA = this.mana;
        this.regenerationMana = 1;
        makeRandomCoordinates();

    }

    public ArrayList<Creature> getTargets() {
        return targets;
    }

    public Creature getTarget() {
        return target;
    }

    public boolean isAlive() {
        return alive;
    }

    public void makeRandomCoordinates(){
        this.x = rand.nextInt(12);
        this.y = rand.nextInt(12);
    }


    public int getArmour() {
        return armour;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    public int getRange() {
        return range;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void chooseTarget(){
        int k = rand.nextInt(targets.size());
        this.target = targets.get(k);
    }

    public void checkTargetsLive(){
        if (!this.target.isAlive()){
            chooseTarget();
        }
    }
    public void attack(){
        if (this.checkRange(this.target) && this.attackPoints > 0 && this.target.armour < this.damage) {
            if (this.damage < this.target.health + this.target.armour) {
                this.target.health = this.target.health - (this.damage - this.target.armour);
                this.attackPoints--;
                this.movePoints = 0;
            } else {
                this.exp+=this.target.expPrice;
                this.target.alive = false;
                this.target.health = 0;
                this.attackPoints--;
                this.movePoints = 0;
            }
        }
    }

    public boolean checkRange(Creature target){
        int n,k;
        if ((this.x - target.x) < 0){
            n = (this.x - target.x)*(-1);
        } else {
            n = this.x - target.x;
        }
        if ((this.y - target.y) < 0){
            k = (this.y - target.y) * (-1);
        } else {
            k = (this.y - target.y);
        }
        if (this.range>=n && this.range>=k){
            return true;
        } else{
            return false;
        }
    }

    public void playerBecomeBetter(String power){
            if (power == "Health" && this.exp>=2){
                this.MAX_HEALTH = this.MAX_HEALTH + 3;
                this.health = this.health + 5;
                this.exp = this.exp - 2;
            } else if (power == "Damage" && this.exp>=2){
                this.damage += 3;
                this.exp = this.exp - 2;
            } else if (power == "Armour" && this.exp >=1 && this.armour<this.MAX_ARMOUR && this.MAX_MOVE_POINTS < this.MAX_INCREASING_MOVE_POINTS){
                this.armour+=1;
                this.exp = this.exp - 1;
                if (this.armour == 4 || this.armour == 8 && this.armour == 12 && this.armour == 16){
                    this.MAX_INCREASING_MOVE_POINTS--;
                }
            } else if (power == "Move Points" && this.exp>=2 && this.MAX_MOVE_POINTS < this.MAX_INCREASING_MOVE_POINTS && this.armour <= this.MAX_ARMOUR - 4){
                this.MAX_MOVE_POINTS++;
                this.exp = this.exp - 2;
                this.MAX_ARMOUR -=4;
            } else if (power == "Attack Points" && this.exp>=3 && this.MAX_ATTACK_POINTS < this.MAX_INCREASING_ATTACK_POINTS && this.range<this.MAX_RANGE){
                this.MAX_ATTACK_POINTS++;
                this.exp = this.exp - 3;
                this.MAX_RANGE--;
            } else if (power == "Range" && this.exp>=4 && this.range<this.MAX_RANGE && this.MAX_ATTACK_POINTS<this.MAX_INCREASING_ATTACK_POINTS){
                this.range++;
                this.exp = this.exp - 4;
                this.MAX_INCREASING_ATTACK_POINTS--;
            } else if (power == "Regeneration" && this.exp>=1 ){
                this.regenerationHealth++;
                this.exp = this.exp - 1;
            }
    }
    public void regenerateHealth(){
        if (this.MAX_HEALTH < this.health + this.regenerationHealth){
            this.health = this.MAX_HEALTH;
        } else {
            this.health+=this.regenerationHealth;
        }
    }
    public void regenerateMana(){
        if (this.MAX_MANA < this.mana + this.regenerationMana){
            this.mana = this.MAX_MANA;
        } else {
            this.mana+=this.regenerationMana;
        }
    }

    public void makeSomeBalanceOfPlayer(){
        if(this.range>1){

        }
    }
}
