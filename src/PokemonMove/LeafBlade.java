package PokemonMove;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class LeafBlade extends PhysicalMove {

    public LeafBlade() {
        super(Type.GRASS, 90, 1);
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
        return "использует 'Leaf Blade'";
    }
}
