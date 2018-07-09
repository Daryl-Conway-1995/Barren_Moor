public class Imp extends Monster {

    public Imp()
    {
        int action = 0;
        setHealthPoints(getRnd(6,5));
        setAttack(getRnd(6,4));
        setDefence(getRnd(3,1));
        setDescription("It's only a small imp. you can take it...right?");
        setAttackChance(50);
        setDefendChance(85);
        setType("Imp");
    }

}
