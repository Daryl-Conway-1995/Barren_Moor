import java.util.Random;

abstract class Monster implements Assult {
    private Random rnd = new Random();
    private int healthPoints = 10;
    private int defence = 0;
    private int attack = 2;
    private int action =0;
    private boolean isAlive = true;
    private int attackChance = 0;
    private int defendChance =0;
    private String type ="";
    private String description = "";
    private boolean isAttacking = false;

    public Monster()
    {

    }

    @Override
    public int attack() {
        System.out.println("The monster attacks.");
        return attack;
    }

    @Override
    public int defend() {
        System.out.println("The monster defends itself.");
        return defence;
    }

    //run away chance has been set to 15% by default.
    @Override
    public boolean runAway() {
        System.out.println("The monster is trying to run away.");
        if(rnd.nextInt(100)<85) {
            System.out.println("The monster tried to flee but failed.");
            return false;
        }
        else
        {
            System.out.println("The monster ran away.");
            isAlive =false;
            return true;
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setDefendChance(int defendChance) {
        this.defendChance = defendChance;
    }

    public void setAttackChance(int attackChance) {
        this.attackChance = attackChance;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getRnd(int higher, int lower) {
        return rnd.nextInt(higher)+lower;
    }

    public int getDefence() {
        return defence;
    }

    public int getAttack() {
        return attack;
    }

    public boolean getIsAttacking()
    {
        return isAttacking;
    }


    public boolean checkIfAlive()
    {
        return isAlive;
    }

    public String getType() {
        return type;
    }

    public void getAction()
    {
        //use to ints to separate 100 into three actions
        // example= 0-50 = attack, 60-100 = defend leaving 50-60 for run away. so 1/2 attack chance, 2/5 defend and a 1/10 chance to ATTEMPT running. running can still fail.
        action = rnd.nextInt(100);
        if (action<=attackChance)
        {
            attack();
            isAttacking =true;
        }else if (action>=defendChance)
        {
            defend();
            isAttacking=false;
        }else {
            runAway();
        }
    }

    public void takeDmg(int dmg)
    {
        healthPoints =healthPoints-dmg;
        if (healthPoints<=0)
        {
            isAlive =false;
        }
    }
}
