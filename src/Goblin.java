public class Goblin extends Monster {

    public Goblin()
    {
        setHealthPoints(getRnd(12,9));
        setAttack(getRnd(10,8));
        setDefence(getRnd(1,0));
        setDescription("It's the same size of you and is carrying a large club.");
        setAttackChance(30);
        setDefendChance(40);
        setType("Goblin");
    }
}
