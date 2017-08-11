package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class MainFrame {
        private JFrame frame1 = new JFrame("frame1");
        private JFrame frame2 = new JFrame("frame2");
        private JFrame frame3 = new JFrame("frame3");
        private JFrame enemyLabelsFrame = new JFrame("Enemy information");
        private JFrame helpFrame = new JFrame("Help");
        private JFrame spellShop = new JFrame("Spell Shop");
        private String name;
        private int step = 0;
        private JLabel nameLabel;
        private JLabel maxHealthLabel;
        private JLabel healthLabel;
        private JLabel movePointsLabel;
        private JLabel attackPointsLabel;
        private JLabel damageLabel;
        private JLabel armourLabel;
        private JLabel rangeLabel;
        private JLabel expLabel;
        private JLabel regenerateLabel;
        private JLabel targetLabel;
        private JLabel enemyHealthLabel;
        private JLabel enemyDamageLabel;
        private JLabel enemyArmourLabel;
        private JLabel enemyTargetLabel;
        private JButton increaseHealthChosen;
        private JButton increaseDamageChosen;
        private JButton increaseArmourChosen;
        private JButton increaseMovePointsChosen;
        private JButton increaseAttackPointsChosen;
        private JButton increaseRangeChosen;
        private JButton increaseRegenerationChosen;
        private JButton healing;
        private JLabel spellLabel;
        private JLabel allyLabel;
        private JLabel manaLabel;
        private JLabel manaRegenLabel;
        int counter = 0;
        ArrayList<Creature> players = new ArrayList<>();
        ArrayList<Creature> monsters = new ArrayList<>();
        JTextField[][] textFields = new JTextField[12][12];
        String[][] map = new String[12][12];
        JTextField firstPlayerNameTextField;
        JTextField secondPlayerNameTextField;
        JRadioButton orcs1;
        JRadioButton orcs2;
        JRadioButton humans1;
        JRadioButton humans2;
        private JButton button1 = new JButton("Next");
        private String firstPlayerName;
        private String secondPlayerName;
        private String firstPlayerRace;
        private String secondPlayerRace;
        private Creature chosen;

        public MainFrame(String name){
            this.name = name;
        }

        public void init() throws InterruptedException{
            initFirstForm();
        }

        public void createMap(){
            for (int i = 0; i < textFields.length; i++){
                for (int j = 0; j < textFields.length; j++){
                    map[i][j] = " ";
                    textFields[i][j] = new JTextField(10);
                    textFields[i][j].setText(map[i][j]);
                    textFields[i][j].setEditable(false);
                    textFields[i][j].setBackground(Color.WHITE);
                    textFields[i][j].addMouseListener(new MyMouseListener(textFields[i][j],i,j));
                    textFields[i][j].addKeyListener(new MyKeyListener());
                    textFields[i][j].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    textFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                    frame2.add(textFields[i][j], new GridBagConstraints(i,j,1,1,1,1,
                            GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,0,0),40,0));
                }
            }
        }

        public void writeMap(){
            for (int i = 0; i < textFields.length; i++){
                for (int j = 0; j < textFields.length; j++){
                    textFields[i][j].setText(map[i][j]);
                }
            }
        }

        public void initFirstForm(){
            firstPlayerNameTextField = new JTextField();
            secondPlayerNameTextField = new JTextField();
            frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setLocationRelativeTo(null);
            frame1.setLayout(new GridBagLayout());
            ButtonGroup bg1 = new ButtonGroup();
            ButtonGroup bg2 = new ButtonGroup();
            orcs1 = new JRadioButton("Orcs");
            humans1 = new JRadioButton("Humans");
            orcs2 = new JRadioButton("Orcs");
            humans2 = new JRadioButton("Humans");
            bg1.add(orcs1);
            bg1.add(humans1);
            bg2.add(orcs2);
            bg2.add(humans2);
            frame1.add(new JLabel("Put the first player name:"), new GridBagConstraints(0,0,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(firstPlayerNameTextField, new GridBagConstraints(1,0,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(new JLabel("Choose the race for the first player:"), new GridBagConstraints(0,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(orcs1, new GridBagConstraints(1,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(humans1, new GridBagConstraints(2,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(new JLabel("Put the second player name:"), new GridBagConstraints(0,3,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(secondPlayerNameTextField, new GridBagConstraints(1,3,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(new JLabel("Choose the race for the second player:"), new GridBagConstraints(0,4,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(orcs2, new GridBagConstraints(1,4,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(humans2, new GridBagConstraints(2,4,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
            frame1.add(button1, new GridBagConstraints(0,5,3,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));

            button1.addActionListener(new MyFirstButtonActionListener());
            frame1.setVisible(true);
        }

        public void initSecondForm(){
            frame1.dispose();
            frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLocationRelativeTo(null);
            frame2.setLayout(new GridBagLayout());
            createMap();
            players.add(new Creature("Alex",20,1,1,1,0,4,0,1, monsters));
            players.add(new Creature("Max",20,1,1,1,0,4,0,1, monsters));
            players.add(new Creature("Thomas",20,1,1,1,0,4,0,1, monsters));
            monsters.add(new Creature("Core",300,0,0,0,10,0,5,1, players));
            for (Creature creature : players){
                creature.frame = this;
            }
            spawn(players.get(0));
            spawn(players.get(1));
            spawn(players.get(2));
            spawn(monsters.get(0));
            players.get(0).chooseTarget();
            players.get(1).chooseTarget();
            players.get(2).chooseTarget();
            monsters.get(0).chooseTarget();
            monstersCheckTargets();
            writePlayers();
            writeMonsters();
            writeMap();
            chosen = players.get(0);

            nameLabel = new JLabel("Name: "+chosen.getName());
            maxHealthLabel = new JLabel("Max Health: "+chosen.getMAX_HEALTH());
            healthLabel = new JLabel("Health: "+chosen.getHealth());
            movePointsLabel = new JLabel("Move Points: "+chosen.movePoints);
            attackPointsLabel = new JLabel("Attack Points: "+chosen.attackPoints);
            damageLabel = new JLabel("Damage: "+chosen.getDamage());
            armourLabel = new JLabel("Armour: "+chosen.getArmour());
            rangeLabel = new JLabel("Range: "+chosen.getRange());
            expLabel = new JLabel("Exp: "+ chosen.exp);
            regenerateLabel = new JLabel("Regeneration : " + chosen.regenerationHealth);
            targetLabel = new JLabel("Target: "+chosen.target.getName());
            spellLabel = new JLabel("Spell: None");
            allyLabel = new JLabel("Ally: None");
            manaLabel = new JLabel("Mana: " + chosen.mana);
            manaRegenLabel = new JLabel("ManaRegen: " + chosen.regenerationMana);

            enemyArmourLabel = new JLabel("Armour: "+chosen.target.getArmour());
            enemyHealthLabel = new JLabel("Health: "+chosen.target.getHealth());
            enemyDamageLabel = new JLabel("Damage: "+ chosen.target.getDamage());
            enemyTargetLabel = new JLabel("Target: "+ chosen.target.target.getName());


            frame2.add(nameLabel,new GridBagConstraints(12,0,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(maxHealthLabel,new GridBagConstraints(12,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

            frame2.add(healthLabel,new GridBagConstraints(12,2,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(movePointsLabel,new GridBagConstraints(12,3,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(attackPointsLabel,new GridBagConstraints(12,4,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(damageLabel,new GridBagConstraints(12,5,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(armourLabel,new GridBagConstraints(12,6,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(rangeLabel,new GridBagConstraints(12,7,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(expLabel,new GridBagConstraints(12,8,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(regenerateLabel,new GridBagConstraints(12,9,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(spellLabel,new GridBagConstraints(12,10,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(allyLabel,new GridBagConstraints(12,11,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(manaLabel,new GridBagConstraints(13,5,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame2.add(manaRegenLabel,new GridBagConstraints(13,6,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));





            colorPlayers();
            colorAllBackground();
            initFrame3();
            initEnemyInformationForm();

            frame2.setVisible(true);
            initHelpFrame();
            initSpellShop();
            helpFrame.setVisible(true);
        }

        public void initHelpFrame(){
            helpFrame.setSize(new Dimension(600,600));
            helpFrame.setLocationRelativeTo(null);
            helpFrame.setLayout(new GridBagLayout());
            helpFrame.add(new JLabel("Press the esc and the help menu will open"),new GridBagConstraints(0,0,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            helpFrame.add(new JLabel("Press the space and the next step will come."),new GridBagConstraints(0,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            helpFrame.add(new JLabel("Press the right click on the one of the players and he will be chosen."),new GridBagConstraints(0,2,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            helpFrame.add(new JLabel("Press the right click on the of the enemy and he will become chosen target of chosen player."),new GridBagConstraints(0,3,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            helpFrame.add(new JLabel("Press the left click on the empty field, and if it close, chosen player will move there."),new GridBagConstraints(0,4,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            helpFrame.add(new JLabel("Press the left click on the chosen enemy, and if enemy enough close, the chosen player will attack him."),new GridBagConstraints(0,5,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            helpFrame.add(new JLabel("Press the middle mouse click on chosen player and you will see the shop."),new GridBagConstraints(0,6,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            helpFrame.add(new JLabel("Press the middle mouse click on chosen enemy and you will see his attributes."),new GridBagConstraints(0,7,6,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            helpFrame.add(new JLabel("Players main target is Core, whe it will be destroyed, players will win."),new GridBagConstraints(0,8,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
        }

        public void initFrame3(){
            frame3.setSize(new Dimension(400,600));
            frame3.setLocationRelativeTo(null);
            frame3.setLayout(new GridBagLayout());
            increaseHealthChosen = new JButton("Increase Health");
            increaseHealthChosen.addActionListener(new IncreaseParamsActionListener("Health"));
            frame3.add(increaseHealthChosen,new GridBagConstraints(0,0,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Exp price = 2"),new GridBagConstraints(1,0,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Increasing = 5"),new GridBagConstraints(2,0,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));


            increaseDamageChosen = new JButton("Increase Damage");
            increaseDamageChosen.addActionListener(new IncreaseParamsActionListener("Damage"));
            frame3.add(increaseDamageChosen,new GridBagConstraints(0,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Exp price = 2"),new GridBagConstraints(1,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Increasing = 3"),new GridBagConstraints(2,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));


            increaseArmourChosen = new JButton("Increase Armour");
            increaseArmourChosen.addActionListener(new IncreaseParamsActionListener("Armour"));
            frame3.add(increaseArmourChosen,new GridBagConstraints(0,2,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Exp price = 1"),new GridBagConstraints(1,2,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Increasing = 1"),new GridBagConstraints(2,2,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));


            increaseMovePointsChosen = new JButton("Increase Move Points");
            increaseMovePointsChosen.addActionListener(new IncreaseParamsActionListener("Move Points"));
            frame3.add(increaseMovePointsChosen,new GridBagConstraints(0,3,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Exp price = 2"),new GridBagConstraints(1,3,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Increasing = 1"),new GridBagConstraints(2,3,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

            increaseAttackPointsChosen = new JButton("Increase Attack Points");
            increaseAttackPointsChosen.addActionListener(new IncreaseParamsActionListener("Attack Points"));
            frame3.add(increaseAttackPointsChosen,new GridBagConstraints(0,4,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Exp price = 3"),new GridBagConstraints(1,4,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Increasing = 1"),new GridBagConstraints(2,4,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

            increaseRangeChosen = new JButton("Increase Range");
            increaseRangeChosen.addActionListener(new IncreaseParamsActionListener("Range"));
            frame3.add(increaseRangeChosen,new GridBagConstraints(0,5,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Exp price = 4"),new GridBagConstraints(1,5,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Increasing = 1"),new GridBagConstraints(2,5,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

            increaseRegenerationChosen = new JButton("Increase Regeneration");
            increaseRegenerationChosen.addActionListener(new IncreaseParamsActionListener("Regeneration"));
            frame3.add(increaseRegenerationChosen,new GridBagConstraints(0,6,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Exp price = 1"),new GridBagConstraints(1,6,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Increasing = 1"),new GridBagConstraints(2,6,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));


            healing = new JButton("Healing");
            healing.addActionListener(new HealingActionListener());
            frame3.add(healing,new GridBagConstraints(0,7,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("Exp price = 2"),new GridBagConstraints(1,7,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            frame3.add(new JLabel("You health will be full "),new GridBagConstraints(2,7,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));


        }

        public void initEnemyInformationForm(){
            enemyLabelsFrame.setSize(new Dimension(400,600));
            enemyLabelsFrame.setLocationRelativeTo(null);
            enemyLabelsFrame.setLayout(new GridBagLayout());
            enemyLabelsFrame.add(targetLabel,new GridBagConstraints(0,0,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            enemyLabelsFrame.add(enemyHealthLabel,new GridBagConstraints(0,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            enemyLabelsFrame.add(enemyArmourLabel,new GridBagConstraints(0,2,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            enemyLabelsFrame.add(enemyDamageLabel,new GridBagConstraints(0,3,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            enemyLabelsFrame.add(enemyTargetLabel,new GridBagConstraints(0,4,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
        }

        public void initSpellShop(){
            spellShop.setSize(new Dimension(400,600));
            spellShop.setLocationRelativeTo(null);
            spellShop.setLayout(new GridBagLayout());
            JButton healButton = new JButton("Buy heal");
            spellShop.add(healButton,new GridBagConstraints(0,0,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            healButton.addActionListener(new SpellBuyActionListener("Healing"));
            JButton swapButton = new JButton("Buy swap");
            spellShop.add(swapButton,new GridBagConstraints(0,1,1,1,1,1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
            swapButton.addActionListener(new SpellBuyActionListener("Swap"));

        }

        public void writePlayers(){
        for (int i = 0; i < players.size(); i++){
            map[players.get(i).getX()][players.get(i).getY()] = players.get(i).getName();
        }
    }

    public void writeMonsters(){
        for (int i = 0; i < monsters.size(); i++){
            map[monsters.get(i).getX()][monsters.get(i).getY()] = monsters.get(i).getName();
        }
    }

    public void makeMonsters(String name, int MAX_HEALTH, int Damage, int Armour,int attackPoints, int range, int price, int movePoints){
        counter++;
        monsters.add(new Creature(name + counter,MAX_HEALTH,2,attackPoints,range,Armour,Damage,0,price, players));
        monsters.get(monsters.size()-1).chooseTarget();
        spawn(monsters.get(monsters.size()-1));
        writeMonsters();
    }

    public void moveTo(String where, Creature creature){
        if (creature.movePoints>0) {
            if (where == "UP" && creature.getY() != 0 && map[creature.x][creature.y - 1] == " ") {
                map[creature.x][creature.y] = " ";
                creature.y--;
                creature.movePoints--;
                writePlayers();
                writeMap();
            } else if (where == "DOWN" && creature.getY() != textFields.length-1 && map[creature.x][creature.y + 1] == " ") {
                map[creature.x][creature.y] = " ";
                creature.y++;
                creature.movePoints--;
                writePlayers();
                writeMap();
            } else if (where == "LEFT" && creature.getX() != 0 && map[creature.x - 1][creature.y] == " ") {
                map[creature.x][creature.y] = " ";
                creature.x--;
                creature.movePoints--;
                writePlayers();
                writeMap();
            } else if (where == "RIGHT" && creature.getX()!= textFields.length-1 && map[creature.x + 1][creature.y] == " ") {
                map[creature.x][creature.y] = " ";
                creature.x++;
                creature.movePoints--;
                writePlayers();
                writeMap();
            }
            writeLabels();
            colorPlayers();
        }
    }

    public void cleverMoving(Creature creature){
        int counter=0;
        int a = new Random().nextInt(2);
        if (counter<6) {
            if (creature.x != map.length - 1 && creature.y != map.length - 1 && creature.x != 0 && creature.y != 0) {
                if (creature.target.x > creature.x && creature.target.y >= creature.y) {
                    if (map[creature.x][creature.y + 1] != " " || map[creature.x + 1][creature.y] != " ") {
                        if (a == 1 && map[creature.x][creature.y - 1] == " ") {
                            moveTo("UP", creature);
                        } else if (map[creature.x - 1][creature.y] == " ") {
                            moveTo("LEFT", creature);
                        } else {
                            findTo(creature);
                        }
                    } else {
                        findTo(creature);
                    }
                } else if (creature.target.x <= creature.x && creature.target.y > creature.y) {
                    if (map[creature.x][creature.y + 1] != " " || map[creature.x - 1][creature.y] != " ") {
                        if (a == 1 && map[creature.x][creature.y - 1] == " ") {
                            moveTo("UP", creature);
                        } else if (map[creature.x + 1][creature.y] == " ") {
                            moveTo("RIGHT", creature);
                        } else {
                            findTo(creature);
                        }
                    } else {
                        findTo(creature);
                    }
                } else if (creature.target.x < creature.x && creature.target.y <= creature.y) {
                    if (map[creature.x][creature.y - 1] != " " || map[creature.x - 1][creature.y] != " ") {
                        if (a == 1 && map[creature.x][creature.y + 1] == " ") {
                            moveTo("DOWN", creature);
                        } else if (map[creature.x + 1][creature.y] == " ") {
                            moveTo("RIGHT", creature);
                        } else {
                            findTo(creature);
                        }
                    } else {
                        findTo(creature);
                    }
                } else if (creature.target.x >= creature.x && creature.target.y < creature.y) {
                    if (map[creature.x][creature.y - 1] != " " || map[creature.x + 1][creature.y] != " ") {
                        if (a == 1 && map[creature.x][creature.y - 1] == " ") {
                            moveTo("DOWN", creature);
                        } else if (map[creature.x - 1][creature.y] == " ") {
                            moveTo("LEFT", creature);
                        } else {
                            findTo(creature);
                        }
                    } else {
                        findTo(creature);
                    }
                } else {
                    findTo(creature);
                }
            } else {
                findTo(creature);
            }
        } else {
            creature.movePoints = 0;
        }
    }

    public void monstersCheckTargets(){
        for (Creature creature : monsters){
            creature.checkTargetsLive();
        }
    }

    public void giveAllPoints(){
        for (Creature creature : players){
           creature.movePoints = creature.MAX_MOVE_POINTS;
           creature.attackPoints = creature.MAX_ATTACK_POINTS;
        }
        for (Creature creature : monsters){
            creature.movePoints = creature.MAX_MOVE_POINTS;
            creature.attackPoints = creature.MAX_ATTACK_POINTS;
        }
    }

    public void colorPlayers(){
        for (int i = 0; i < textFields.length; i++){
            for (int j = 0; j < textFields.length; j++){
                for (Creature creature : players){
                    if (creature.x == i && creature.y == j){
                        textFields[i][j].setForeground(Color.BLUE);
                    }
                }
                for (Creature creature : monsters){
                    if (creature.x == i && creature.y == j){
                        textFields[i][j].setForeground(Color.RED);
                    }
                }
            }
        }
    }
    public void colorAllBackground(){
        for (int i = 0; i < textFields.length; i++){
            for (int j = 0; j < textFields.length; j++){
                boolean bool = false;
                if (!players.isEmpty()) {
                    for (Creature creature : players) {
                        if (creature.x == i && creature.y == j) {
                            if (creature.equals(chosen)) {
                                textFields[i][j].setBackground(Color.BLACK);
                                bool = true;
                            } else {
                                textFields[i][j].setBackground(Color.WHITE);
                                bool = true;
                            }
                        }
                    }
                } for (Creature creature : monsters) {
                    if (creature.x == i && creature.y == j) {
                        if (creature.equals(chosen.target)) {
                            textFields[i][j].setBackground(Color.BLACK);
                            bool = true;
                        } else {
                            textFields[i][j].setBackground(Color.WHITE);
                            bool = true;
                        }
                    }
                }
                if (!bool){
                    textFields[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

//    public void colorTarget(){
//        if (!monsters.isEmpty()) {
//            for (Creature creature : monsters) {
//                if (creature.equals(chosen.target)){
//                    textFields[creature.target.x][creature.target.y].setBackground(Color.BLACK);
//                } else {
//                    textFields[creature.x][creature.y].setBackground(Color.WHITE);
//                }
//            }
//        }
//    }
//
//    public void colorChosenPlayer(){
//        if (!players.isEmpty()) {
//            for (Creature creature : players) {
//                if (creature.equals(chosen)){
//                    textFields[creature.x][creature.y].setBackground(Color.BLACK);
//                } else {
//                    textFields[creature.x][creature.y].setBackground(Color.WHITE);
//                }
//            }
//        }
//    }

    public void findTo(Creature creature){
        Random random = new Random();
        int a = random.nextInt(2);
        if (creature.target.x > creature.x && creature.target.y > creature.y){
            if (a == 0 && (creature.target.x != creature.x && creature.target.y == creature.y)){
                moveTo("RIGHT",creature);
            } else {
                moveTo("DOWN",creature);
            }
        }
        if (creature.target.x > creature.x && creature.target.y <= creature.y){
            if (a == 0 && (creature.target.x != creature.x && creature.target.y == creature.y)){
                moveTo("RIGHT",creature);
            } else {
                moveTo("UP",creature);
            }
        }
        if (creature.target.x <= creature.x && creature.target.y >= creature.y){
            if (a == 0 && (creature.target.x != creature.x && creature.target.y == creature.y)){
                moveTo("LEFT",creature);
            } else {
                moveTo("DOWN",creature);
            }
        }
        if (creature.target.x <= creature.x && creature.target.y <= creature.y){
            if (a == 0 && (creature.target.x != creature.x && creature.target.y == creature.y)){
                moveTo("LEFT",creature);
            } else {
                moveTo("UP",creature);
            }
        }
    }

    public void monstersLive(){
        for (Creature creature : monsters){
            int counter = 0;
            for (Creature target : players) {
                if (creature.checkRange(target)) {
                    creature.target = target;
                }
            }
            if (!creature.checkRange(creature.target)){
                while (!creature.checkRange(creature.target) && creature.movePoints>0){
                    cleverMoving(creature);
                    counter++;
                    for (Creature target : players) {
                        if (creature.checkRange(target)) {
                            creature.target = target;
                        }
                    }
                    if (counter==6){
                        break;
                    }
                }
                if (creature.checkRange(creature.target) && creature.target.armour < creature.damage){
                    while (creature.attackPoints>0){
                        creature.attack();
                    }
                }
            } else {
                while (creature.attackPoints>0 && creature.target.armour < creature.damage){
                    creature.attack();
                }
            }
        }
    }

    public boolean checkWin(){
        boolean res = true;
        for (Creature creature : monsters){
            if (creature.getName() == "Core"){
                res = false;
            }
        }
        return res;
    }

    public void regenerateAll(){
        for (Creature creature : players){
            creature.regenerateHealth();
        }
        for (Creature creature : monsters){
            creature.regenerateHealth();
        }
        for (Creature creature : players){
            creature.regenerateMana();
        }
        for (Creature creature : monsters){
            creature.regenerateMana();
        }
    }

    public boolean checkPlaceForEmpty(Creature creature){
        boolean res = true;
        for (int i = 0; i < players.size(); i++){
            if (creature.x == players.get(i).x && creature.y == players.get(i).y && !creature.equals(players.get(i))){
                res = false;
            }
        }
        for (int i = 0; i < monsters.size(); i++){
            if (creature.x == monsters.get(i).x && creature.y == monsters.get(i).y && !creature.equals(monsters.get(i))){
                res = false;
            }
        }
        return res;
    }

    public void spawn(Creature creature){
        while (!checkPlaceForEmpty(creature)){
            creature.makeRandomCoordinates();
        }
    }

    public void cleanUpCreatures(){
        for (int i = 0; i < monsters.size(); i++){
            if (monsters.get(i).alive!=true){
                map[monsters.get(i).x][monsters.get(i).y] = " ";
                monsters.remove(i);
                writeMap();
            }
        }
        for (int i = 0; i < players.size(); i++){
            if (players.get(i).alive!=true){
                map[players.get(i).x][players.get(i).y] = " ";
                players.remove(i);
                writeMap();
            }
        }
    }


    public void writeLabels(){
        nameLabel.setText("Name: "+chosen.getName());
        maxHealthLabel.setText("Max Health: "+chosen.getMAX_HEALTH());
        healthLabel.setText("Health: "+chosen.getHealth());
        movePointsLabel.setText("Move Points: "+chosen.movePoints);
        attackPointsLabel.setText("Attack Points: "+chosen.attackPoints);
        damageLabel.setText("Damage: "+chosen.getDamage());
        armourLabel.setText("Armour: "+chosen.getArmour());
        targetLabel.setText("Target: "+chosen.target.getName());
        rangeLabel.setText("Range: "+chosen.getRange());
        regenerateLabel.setText("Regeneration: "+chosen.regenerationHealth);
        expLabel.setText("Exp: "+chosen.exp);
        manaLabel.setText("Mana: " + chosen.mana);
        manaRegenLabel.setText("ManaRegen: " + chosen.regenerationMana);
        if (!chosen.spells.isEmpty()){
            spellLabel.setText("Spell: "+chosen.chosenSpell.name);
        } else {
            spellLabel.setText("Spell: None");
        }
        if (chosen.allyTarget != null){
            allyLabel.setText("Ally: " + chosen.allyTarget.getName());
        }

        enemyArmourLabel.setText("Armour: "+chosen.target.getArmour());
        enemyHealthLabel.setText("Health: "+chosen.target.getHealth());
        enemyDamageLabel.setText("Damage: "+ chosen.target.getDamage());
        enemyTargetLabel.setText("Target: "+ chosen.target.target.getName());
    }

    public void nextStep(){
        if (players.isEmpty()){
            System.out.println("Lost");
        } else if (checkWin()){
            System.out.println("Win");
        } else {

            step++;
            regenerateAll();
            if (step % 5 == 0){
                map[monsters.get(0).x][monsters.get(0).y] = " ";
                monsters.get(0).makeRandomCoordinates();
                spawn(monsters.get(0));
                for (Creature creature : players){
                    creature.exp++;
                }
                writeMonsters();
                writeMap();
                colorAllBackground();
            }
            monstersLive();
            int a = new Random().nextInt(10);
            if (step<=10) {
                if (a<=5) {
                    makeMonsters("Sm Goblin ", 4+(int)step/2,2+(int)step/3,0,1,1,1,2);
                    makeMonsters("Sm Goblin ", 4+(int)step/2,2+(int)step/3,0,1,1,1,2);
                    makeMonsters("Sm Berserk ", 4+(int)step/2,2+(int)step/3,0,2,1,1,3);
                } else if (a>5 && a<8){
                    makeMonsters("Sm Ork ",6 + (int)step/2,4+(int)step/3,0,1,1,1,1);
                    makeMonsters("Sm Ork ",6 + (int)step/2,4+(int)step/3,0,1,1,1,1);
                } else {
                    makeMonsters("Sm Archer ",4 + (int)step/2,2+(int)step/3,0,1,3,1,1);
                }
            } else if (step > 10 && step <= 25){
                if (a<4){
                    makeMonsters("Mid Goblin ", 6+(int)step/2,4+(int)step/4,1,1,1,1,2);
                    makeMonsters("Mid Goblin ", 6+(int)step/2,4+(int)step/4,1,1,1,1,2);
                    makeMonsters("Mid Berserk ", 6+(int)step/2,4+(int)step/4,0,2,1,1,3);
                } else if(a>=4 && a<6){
                    makeMonsters("Mid Ork ",10+(int)step/2,6+(int)step/4,2,1,1,2,1);
                    makeMonsters("Mid Ork ",10+(int)step/2,6+(int)step/4,2,1,1,2,1);
                } else if(a>=6 && a<8){
                    makeMonsters("Mid Archer ",6+(int)step/2,4+(int)step/4,2,1,3,1,1);
                    makeMonsters("Mid Archer ",6+(int)step/2,4+(int)step/4,2,1,3,1,1);
                } else {
                    makeMonsters("Mid Troll ",15+(int)step/2,10+(int)step/4,3,1,1,2,1);
                }
            } else {
                if (a<4){
                    makeMonsters("Big Goblin ", 10+(int)step/2,8+(int)step/4,2,1,1,1,2);
                    makeMonsters("Big Goblin ", 10+(int)step/2,8+(int)step/4,2,1,1,1,2);
                    makeMonsters("Big Goblin ", 10+(int)step/2,8+(int)step/4,2,1,1,1,2);
                    makeMonsters("Big Berserk ", 10+(int)step/2,8+(int)step/4,0,2,1,1,3);
                } else if(a>=4 && a<6){
                    makeMonsters("Big Ork ",15+(int)step/2,12+(int)step/4,4,1,1,2,2);
                    makeMonsters("Big Ork ",15+(int)step/2,12+(int)step/4,4,1,1,2,2);
                    makeMonsters("Big Berserk ", 10+(int)step/2,8+(int)step/4,0,2,1,2,3);
                } else if(a>=6 && a<8){
                    makeMonsters("Big Archer ",8+(int)step/2,6+(int)step/4,2,1,3,1,1);
                    makeMonsters("Big Archer ",8+(int)step/2,6+(int)step/4,2,1,3,1,1);
                }else {
                    makeMonsters("Big Troll ",20+(int)step/2,20+(int)step/4,4+(int)step/4,1,1,2,1);
                    makeMonsters("Big Troll ",20+(int)step/2,20+(int)step/4,4+(int)step/4,1,1,2,1);
                }
            }
            writeMonsters();
            for (Creature creature : monsters) {
                creature.checkTargetsLive();
            }
            for (Creature creature : players) {
                creature.checkTargetsLive();
            }
            giveAllPoints();
            cleanUpCreatures();
            writeMap();
            writeLabels();
            colorPlayers();
            colorAllBackground();
        }
    }


    public class MyFirstButtonActionListener implements ActionListener{

        public void actionPerformed(ActionEvent event){
            firstPlayerName = firstPlayerNameTextField.getText();
            secondPlayerName = secondPlayerNameTextField.getText();
            if (orcs1.isSelected()){
                firstPlayerRace = "orcs";
            } else{
                firstPlayerRace = "humans";
            }
            if (orcs2.isSelected()){
                secondPlayerRace = "orcs";
            } else{
                secondPlayerRace = "humans";
            }
            initSecondForm();
        }
    }

    public  class SpellBuyActionListener implements ActionListener{
        private String name;
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean res = true;
            for (Spell spell : chosen.spells){
                if(spell.name == this.name){
                    res = false;
                }
            }
            if (res && chosen.exp>=2){
                chosen.spells.add(new Spell(name,chosen.frame,chosen));
                chosen.chosenSpell = chosen.spells.get(chosen.spells.size()-1);
                chosen.exp-=2;
                writeLabels();
            }
        }
        public SpellBuyActionListener(String name){
            this.name = name;
        }
    }





    public class IncreaseParamsActionListener implements ActionListener{
        private String params;
        @Override
        public void actionPerformed(ActionEvent e) {
            chosen.playerBecomeBetter(params);
            writeLabels();
        }
        public IncreaseParamsActionListener(String params){
            this.params = params;
        }
    }
    public class HealingActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (chosen.exp >= 2){
                chosen.health = chosen.MAX_HEALTH;
                chosen.exp = chosen.exp - 2;
                writeLabels();
            }
        }
    }

    class MyMouseListener implements MouseListener {
        private JTextField textField;
        public int x,y;
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (MouseEvent.BUTTON1 == e.getButton()) {
                if (chosen.target.x == this.x && chosen.target.y == this.y) {
                    chosen.attack();
                    cleanUpCreatures();
                } else {
                    if (this.x - chosen.x == 1 && this.y - chosen.y == 0) {
                        moveTo("RIGHT", chosen);
                        colorAllBackground();
                    } else if (this.x - chosen.x == -1 && this.y - chosen.y == 0) {
                        moveTo("LEFT", chosen);
                        colorAllBackground();
                    } else if (this.x - chosen.x == 0 && this.y - chosen.y == 1) {
                        moveTo("DOWN", chosen);
                        colorAllBackground();
                    } else if (this.x - chosen.x == 0 && this.y - chosen.y == -1) {
                        moveTo("UP", chosen);
                        colorAllBackground();
                    }
                } for (Creature creature : players){
                    if (this.x == creature.x && this.y == creature.y){
                        chosen.allyTarget = creature;
                    }
                }
            } else if (MouseEvent.BUTTON3 == e.getButton()){
                for (Creature creature : monsters) {
                    if (creature.x == this.x && creature.y == this.y) {
                        chosen.target = creature;
                        colorAllBackground();
                    }
                }
                if (!players.isEmpty()) {
                    for (Creature creature : players) {
                        if (creature.x == this.x && creature.y == this.y) {
                            chosen = creature;
                            colorAllBackground();
                            colorAllBackground();
                        }
                    }
                }
            } else if (MouseEvent.BUTTON2 == e.getButton()){
                if (this.x == chosen.target.x && this.y == chosen.target.y){
                    writeLabels();
                    enemyLabelsFrame.setVisible(true);
                } else if (this.x == chosen.x && this.y == chosen.y){
                    frame3.setVisible(true);
                }
            } 
            writeLabels();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
        public MyMouseListener(JTextField textField, int x, int y){
            this.textField = textField;
            this.x = x;
            this.y = y;
        }
    }
    class MyKeyListener implements KeyListener{
        @Override
        public void keyPressed(KeyEvent e) {
            if (KeyEvent.VK_SPACE == e.getKeyChar()){
                nextStep();
            } else if (KeyEvent.VK_ESCAPE == e.getKeyChar()){
                helpFrame.setVisible(true);
            } else if (KeyEvent.VK_Q == e.getKeyChar()){
                spellShop.setVisible(true);
            } else if (KeyEvent.VK_E == e.getKeyChar()){
                if (!chosen.spells.isEmpty()) {
                    chosen.chosenSpell.doMagic();
                    writeLabels();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    }
    }

