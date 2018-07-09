public class Giant extends Monster{

    public Giant()
    {
        setHealthPoints(getRnd(25,15));
        setAttack(getRnd(15,10));
        setDefence(getRnd(5,4));
        setDescription("HOW THE HELL DID THIS SNEAK UP ON YOU?!");
        setAttackChance(50);
        setDefendChance(60);
        setType("Giant");
    }
}
