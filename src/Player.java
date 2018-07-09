import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player implements Assult {

    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);
    private Entity[] playerInventory = new Entity[5];
    private int inventoryCounter =0;
    private int healthPoints = 100;
    private boolean isPlayerAlive = true;
    private int defence = 4;
    private int attack = 10;


    public boolean addToInventory(Entity e)
    {
        if (inventoryCounter <5)
        {
            playerInventory[inventoryCounter] = e;
            System.out.println("You add " +e.getName()+ " to your inventory");
            inventoryCounter++;
            return true;
        }else
        {
            return false;
        }
    }

    public void checkInventory()
    {
        for (int i = 0; i<5; i++)
        {
            if(playerInventory[i] != null) {
                System.out.println("[" + (i+1) + "] " + playerInventory[i].getName());
            }else
            {
                System.out.println("[" + (i+1) + "] empty");
            }
        }
    }

    public void useItem(int i){
        if(playerInventory[i].getName().equals("mushroom"))
        {healthPoints=healthPoints+10;
        System.out.println("you feel better.");}
        if(playerInventory[i].getName().equals("sword"))
        {attack=attack+10;
        System.out.println("you feel better equipped.");}
        if(playerInventory[i].getName().equals("shield"))
        {defence=defence+10;
        System.out.println("you feel more protected.");}
        if(playerInventory[i].getName().equals("potion"))
        {healthPoints=healthPoints+100;
        System.out.println("you feel like a GOD.");}
        playerInventory[i] =null;
        inventoryCounter--;
    }

    //method overloading
    public Entity addToInventory(Entity e,int i)
    {
        Entity temp = playerInventory[i];
        playerInventory[i] = e;
        return temp;
    }


    public int getAttack() {
        return attack;
    }

    public void getHealthPoints() {
        if (healthPoints>0) {
            System.out.println("You have " + healthPoints + "hp remaining.");
        }else
        {
            System.out.println("You have 0hp remaining. you fall in battle. GAME OVER.");
        }
    }

    public int getHP()
    {
        return healthPoints;
    }

    public int getDefence() {
        return defence;
    }

    public int getInventoryCounter() {
        return inventoryCounter;
    }

    @Override
    public void takeDmg(int dmg) {
        healthPoints =healthPoints-dmg;
        if (healthPoints<=0)
        {
            isPlayerAlive =false;
        }
    }


    @Override
    public int attack() {
        System.out.println("You aim for the face.");
        return attack;
    }

    @Override
    public int defend() {
        System.out.println("You prepare yourself for the worst.");
        return defence;
    }

    //run away chance has been set to 15% by default.
    @Override
    public boolean runAway() {
        System.out.println("You attempt to flee");
        if(rnd.nextInt(100)<85) {
            System.out.println("The monster manages to keep up with you.");
            return false;
        }
        else
        {
            System.out.println("You managed to lose the monster.");
            return true;
        }
    }

    @Override
    public boolean checkIfAlive() {
        return isPlayerAlive;
    }
}
