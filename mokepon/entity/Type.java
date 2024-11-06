package entity;

public class Type {
    Type[] resistances;
    Type[] immunities;
    Type[] effectives;
    
    public static Type fire = new Type();
    public static Type water = new Type();
    public static Type grass = new Type();

    public static Type dragon = new Type();
    public static Type fairy = new Type();
    public static Type poison = new Type();

    public Type() {
        Type[] res_fire = {grass};
        Type[] res_water = {fire};
        Type[] res_grass = {water};

        Type[] res_dragon = {fire, water, grass};
        Type[] res_poison = {grass, fairy};
        Type[] imm_fairy = {dragon};


        Type[] eff_fire = {grass};
        Type[] eff_water = {fire};
        Type[] eff_grass = {water};

        Type[] eff_dragon = {dragon};
        Type[] eff_poison = {fairy};
        Type[] eff_fairy = {dragon};

        


        Type.fire.setInteractions(res_fire, eff_fire, null);
        Type.water.setInteractions(res_water, eff_water, null);
        Type.grass.setInteractions(res_grass, eff_grass, null);

        Type.dragon.setInteractions(res_dragon, eff_dragon, null);
        Type.poison.setInteractions(res_poison, eff_poison, null);
        Type.fairy.setInteractions(null, eff_fairy, imm_fairy);
    }

    public void setInteractions(Type[] resistances, Type[] effectives, Type[] immunities) {
        this.resistances = resistances;
        this.effectives = effectives;
        this.immunities = immunities;
    }

    

}


