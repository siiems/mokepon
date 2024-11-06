package move;

import entity.Type;

public class Attack extends Move {
    public double Damage;
    public boolean PhysicalAttack;

    public Attack(Type type, double Damage, boolean PhysicalAttack, double Accuracy) {
        this.Type = type;
        this.Damage = Damage;
        this.PhysicalAttack = PhysicalAttack;
        this.Accuracy = Accuracy;
    }
}
