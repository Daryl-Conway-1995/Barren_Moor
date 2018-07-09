import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BarrenMoor {

    //set up non player variables
    private boolean isReplaying = true;
    private boolean isGameRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private String surroundings = "a foul smelling bog that appears to be full of strange grey plants. the water reaches your knees";
    private Entity currentEntity = new Entity();
    private Monster currentMonster;
    private int[] rndLocation = new int[6];
    Shield shield = new Shield();
    Sword sword = new Sword();
    Mushroom mush = new Mushroom();

    //set up monsters and items
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private ArrayList<Entity> entities = new ArrayList<Entity>();

    //set up player interaction variables
    private Player player = new Player();
    private ArrayList<String> playerOptions = new ArrayList<String>();
    private String playerChoice = "";

    public  BarrenMoor() {

        //sets up continues loop if replay is wanted.
        while (isReplaying) {
            //start of game. set up basic actions and pre-randomise events.
            setLocation(1);
            setLocation(2);
            setLocation(3);
            monsters.clear();
            entities.clear();

            //populate area with monsters sudo-random amount of each.
            for (int i = 0; i < random.nextInt(5) + 10; i++) {
                Imp imp = new Imp();
                monsters.add(imp);
            }
            for (int i = 0; i < random.nextInt(10) + 5; i++) {
                Goblin goblin = new Goblin();
                monsters.add(goblin);
            }
            for (int i = 0; i < random.nextInt(4) + 1; i++) {
                Giant giant = new Giant();
                monsters.add(giant);
            }
            for (int i = 0; i < random.nextInt(9) + 1; i++) {
                entities.add(mush);
            }
            for (int i = 0; i < random.nextInt(2) + 1; i++) {
                entities.add(sword);
            }
            for (int i = 0; i < random.nextInt(5) + 1; i++) {
                entities.add(shield);
            }
            StrangePotion potion = new StrangePotion();
            entities.add(potion);
            Dragon dragon = new Dragon();
            dragon.setDescription("wait how dare you turn on me you measly human. i brought you here for entertainment, \n you think you can take me on?! i will destroy you!");
            monsters.add(dragon);

            //introduction + getting basic options
            playerOptions.clear();
            playerOptions.add("look");
            System.out.println("You awaken feeling like you just been hit by a small car.]");
            printOptions();
            takePlayerChoice();
            checkChoice();
            System.out.println("A small glint of light reflects in the nearby water.");
            playerOptions.add("interact");
            playerOptions.remove("look");
            while (!(playerChoice.equals("interact"))) {
                printOptions();
                takePlayerChoice();
                if (playerChoice.equals("interact")) {
                    System.out.println("it appears to be a " + currentEntity.getName() + ". You pick it up without thinking.");
                    playerOptions.add("compass");
                    playerOptions.add("look");
                    currentEntity = null;
                }
            }
            currentEntity = null;

            //run random events till player dies or game is won (found way home(random event))
            while (isGameRunning) {
                printOptions();
                takePlayerChoice();
                checkChoice();

                //add lose condition
                if(player.getHP()<=0)
                {
                    playerOptions.clear();
                    playerOptions.add("yes");
                    playerOptions.add("no");
                    System.out.println("Would you like to try again?");
                    printOptions();
                    takePlayerChoice();
                    if(playerChoice.equals("no"))
                    {
                        isReplaying = false;
                    }
                    isGameRunning =false;
                }
            }
        }
    }

    private void event()
    {
        //set random events monsters and items. currently 80% chance of monsters and 20% chance of items.
        if (random.nextInt(100) <80)
        {
            currentMonster = monsters.get(random.nextInt(monsters.size()));
            System.out.println("Suddenly a " + currentMonster.getType()+ " appears out of nowhere. It rushes you before you can react.");
            setPlayerOptions(true,false);
        }else
        {
            currentEntity = entities.get(random.nextInt(entities.size()));
            System.out.println("As you explore you come across a " + currentEntity.getName()+ ". you are unsure about its state but better then nothing.");
            setPlayerOptions(false,false);
        }
    }

    //lazy way of setting player inputs.
    private void setPlayerOptions(boolean isBattle,boolean isInBag)
    {
        if(isBattle)
        {
            playerOptions.clear();
            playerOptions.add("inspect");
            playerOptions.add("attack");
            playerOptions.add("defend");
            playerOptions.add("run");
        } else if(isInBag)
        {
            playerOptions.clear();
            playerOptions.add("0");
            playerOptions.add("1");
            playerOptions.add("2");
            playerOptions.add("3");
            playerOptions.add("4");
            playerOptions.add("5");
            takePlayerChoice();
        }else{
            playerOptions.clear();
            playerOptions.add("interact");
            playerOptions.add("compass");
            playerOptions.add("look");
            playerOptions.add("bag");
        }
    }

    //lazy way of outputting user options.
    private void printOptions() {
        System.out.println();
        for (String s : playerOptions) {
            System.out.print("[" + s + "] ");
        }
    }

    //lazy intake of user option.
    private void takePlayerChoice() {
        playerChoice =scanner.nextLine().toLowerCase();
    }

    //check to see what occurs for each choice option.
    private void checkChoice() {
        while (!(playerOptions.contains(playerChoice))) {
            System.out.println("You seem to have hit your head as you've forgotten how to do basic tasks .... not a good sign.");
            printOptions();
            takePlayerChoice();
        }
        if(playerChoice.equals("bag"))
        {
            //check bag for items
            player.checkInventory();
            if(player.getInventoryCounter()>0) {
                System.out.println("would you like to use an item? [1][2][3][4][5]   [0] will cancel");
                setPlayerOptions(false,true);
                if (Integer.parseInt(playerChoice)==0)
                {
                    setPlayerOptions(false,false);
                }
                else{
                    player.useItem((Integer.parseInt(playerChoice)-1));
                    setPlayerOptions(false,false);
                }
            }
        }

        //go to new event
        if (playerChoice.equals("compass")) {
            String direction ="";
            for (int i =0;i<rndLocation.length;i++)
            {
                double aSquared = 0.0;
                double bSquared =0.0;
                if(i%2==0)
                {
                    System.out.println();
                    System.out.print("["+((i+2)/2)+"]");
                    aSquared = (double)rndLocation[i]*rndLocation[i];
                    if(rndLocation[i]<0)
                    {
                        System.out.print("The compass points "+Math.abs(rndLocation[i])+" south ");
                    }else
                    {
                        System.out.print("The compass points "+Math.abs(rndLocation[i])+" north ");
                    }
                }else
                {
                    bSquared = (double)rndLocation[i]*rndLocation[i];
                    if(rndLocation[i]<0)
                    {
                        System.out.print(" and "+Math.abs(rndLocation[i])+" east.");
                    }else
                    {
                        System.out.print(" and "+Math.abs(rndLocation[i])+" west.");
                    }
                }
            }
            System.out.println("\nWhich location do you wish to head to? [1][2][3]");
            takePlayerChoice();
            while (!(playerChoice.equals("1")||playerChoice.equals("2")||playerChoice.equals("3")))
            {
                System.out.println("It's higher then 0, less then 4. try again");
                System.out.println("Which location do you wish to head to? [1][2][3]");
                takePlayerChoice();
            }
            setLocation(Integer.parseInt(playerChoice));
            event();
        }

        //look at the surroundings (never changes, not implemented randomise)
        if (playerChoice.equals("look")) {
            System.out.println(surroundings);
        }

        //interact with item
        if (playerChoice.equals("interact")) {
            if (currentEntity ==null)
            {
                //no item
                System.out.println("There is noting here.");
            }
            else {
                System.out.println("it appears to be a " + currentEntity.getName() + " would you like to pick it up? [yes][no]");
                takePlayerChoice();
                //there is an item
                while (!(playerChoice.equals("yes") || playerChoice.equals("no"))) {
                    System.out.println("I don't think you want to do that.");
                    System.out.print("it appears to be a " + currentEntity.getName() + " would you like to pick it up? [yes][no]");
                    takePlayerChoice();
                }
                if (playerChoice.equals("yes")) {
                    if (!(player.addToInventory(currentEntity))) { //if possible add to bag, if not ask user if they want to swap items.
                        System.out.println("Your bag is full. Do you wish to replace " + currentEntity.getName() + " with something? [yes][no]");
                        playerChoice = scanner.nextLine();
                        while (!(playerChoice.equals("yes") || playerChoice.equals("no"))) {
                            System.out.println("That is not an option.");
                            System.out.println("Do you wish to replace " + currentEntity.getName() + " with something? [yes][no]");
                            takePlayerChoice();
                        }
                        if (playerChoice.equals("yes")) {
                            System.out.println("Which item do you want to replace?");
                            player.checkInventory();
                            takePlayerChoice();
                            currentEntity =player.addToInventory(currentEntity,Integer.getInteger(playerChoice));
                        }
                    }
                    else {
                        //destroy items added to bag.
                        currentEntity =null;
                    }
                } else {
                    System.out.println("You ignore the " + currentEntity.getName() + " and move on.");
                }
            }
        }

        //inspect monster
        if(playerChoice.equals("inspect"))
        {
            //moster info
            System.out.println("The monster apears to be a "+currentMonster.getType()+" ...they don't seem to like you.");
            System.out.println(currentMonster.getDescription());
        }

        //attack monster
        if(playerChoice.equals("attack"))
        {
            //do dmg
            player.attack();
            currentMonster.getAction();
            if(currentMonster.getIsAttacking()) {
                player.takeDmg(currentMonster.getAttack() - player.getDefence());
                currentMonster.takeDmg(player.getAttack() - currentMonster.getDefence());
                player.getHealthPoints();
                if(!currentMonster.checkIfAlive())
                {
                    //is monster dead?
                    if (currentMonster.getType().equals("ELDER DRAGON")) //is monster end boss and dead?
                    {
                        System.out.println("OK WAIT! YOU WIN! I WILL SEND YOU BACK HOME IF YOU WISH!");
                        System.out.println("([yes] win the game and return home, [no] stay in this world and FINISH HIM, [~] blood lust not satisfied yet play again)");
                        playerOptions.clear();
                        playerOptions.add("yes"); //end game
                        playerOptions.add("no"); //end game (evil)
                        playerOptions.add("~"); //replay
                        printOptions();
                        takePlayerChoice();
                        if(playerChoice.equals("yes"))
                        {
                            System.out.println("VERY WELL YOU HAVE YOUR WAY.");
                            System.out.println("BEGONE AND NEVER RETURN");
                            isReplaying =false;
                        }else if(playerChoice.equals("no")) {
                            System.out.println("NOOOOOOO HOW DARE YOU!");
                            System.out.println("(as the dragon attempts to make one last attack you plunge your sword in to it's heart,\n ripping it open and being bathed in the dragons still warm blood.)");
                            System.out.println("(as you look at the desecrated remains of the dragon you fill with unyielding blood lust..... you want to kill more.)");
                            isReplaying = false;
                        }
                        isGameRunning=false;

                    }else {
                        //if generic monster, output generic victory.
                        System.out.println("You emerge victorious and head on your way.");
                        setPlayerOptions(false, false);
                        currentMonster = null;
                    }
                }
            }else {
                //if not dead, deal damage to monster
                currentMonster.takeDmg(player.getAttack() - (currentMonster.getDefence()*2));
            }
        }

        //defend against monster. reduces incoming damage
        if(playerChoice.equals("defend"))
        {
            player.defend();
            currentMonster.getAction();
            if(currentMonster.getIsAttacking()) {
                player.takeDmg(currentMonster.getAttack() - (player.getDefence()*2));
                player.getHealthPoints();
            }else {
                //both defend  completely uneventful.
                System.out.println("You both stare at ech other for awhile. both too scared to move.");
            }
        }

        //run away from monster.
        if(playerChoice.equals("run"))
        {
            if (player.runAway())
            {
                //run away works, destroy current monster
                System.out.println("You successfully managed to run away.");
                currentMonster = null;
                setPlayerOptions(false,false);
            }
            else {
                //if fail, monster gets free move.
                currentMonster.getAction();
                if (currentMonster.getIsAttacking()) {
                    player.takeDmg(currentMonster.getAttack() - player.getDefence());
                    player.getHealthPoints();
                } else {
                    System.out.println("It seems hard to escape them.");
                }

            }
        }
    }

    //randomise locations
    private void setLocation(int i)
    {
        rndLocation[(i*2)-2]=(random.nextInt(+10)-5);
        rndLocation[(i*2-1)]=(random.nextInt(+10)-5);
    }

}
