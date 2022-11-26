package pokemonMove;

import ru.ifmo.se.pokemon.*;

public class RazorLeaf extends PhysicalMove {

    public RazorLeaf() {
        super(Type.GRASS, 55,0.95);
    }

    @Override
    protected double calcCriticalHit(Pokemon pokemon, Pokemon pokemon1) {
        if (pokemon.getStat(Stat.SPEED) / 170.0 > Math.random()) {
            System.out.println("Критический удар!");
            return 2.0;
        } else {
            return 1.0;
        }
    }

    @Override
    protected String describe() {
        return "использует 'Razor Leaf'";
    }
}
