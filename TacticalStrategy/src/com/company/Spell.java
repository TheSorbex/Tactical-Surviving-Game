package com.company;

public class Spell {
    public String name;
    public MainFrame frame;
    public Creature speller;

    public Spell(String name, MainFrame frame, Creature speller){
        this.name = name;
        this.frame = frame;
        this.speller = speller;
    }

    public void doMagic(){
        if (this.name == "Healing"){
            this.heal();
        } if (this.name == "Swap"){
            this.swap();
        }
    }

    public void heal(){
        if (speller.mana >= 2){
            if (speller.allyTarget.health!=speller.allyTarget.MAX_HEALTH){
                speller.allyTarget.health = speller.allyTarget.MAX_HEALTH;
                speller.mana-=2;
                frame.writeLabels();
            }
        }
    }
    public void swap(){
        if (speller.mana>=2){
            int x1 = this.speller.x;
            int y1 = this.speller.y;
            int x2 = this.speller.allyTarget.x;
            int y2 = this.speller.allyTarget.y;
            this.speller.x = x2;
            this.speller.y = y2;
            this.speller.allyTarget.x = x1;
            this.speller.allyTarget.y = y1;
            frame.map[speller.x][speller.y] = speller.allyTarget.getName();
            frame.map[speller.allyTarget.x][speller.allyTarget.y] = speller.getName();
            frame.writePlayers();
            frame.writeMap();
            frame.colorAllBackground();
            speller.mana-=2;
            frame.writeLabels();
        }
    }
}
