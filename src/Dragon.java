public class Dragon extends Monster {
    public Dragon()
    {
        setHealthPoints(getRnd(100,20));
        setAttack(getRnd(10,30));
        setDefence(getRnd(10,10));
        setAttackChance(30);
        setDefendChance(31);
        setType("ELDER DRAGON");
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description.toUpperCase());
    }
}
