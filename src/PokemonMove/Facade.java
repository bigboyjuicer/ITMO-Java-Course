package PokemonMove;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Facade extends PhysicalMove {

    public Facade() {
        super(Type.NORMAL, 70, 1);
    }

    @Override
    protected double calcBaseDamage(Pokemon pokemon, Pokemon pokemon1) {

        return switch (pokemon.getCondition()) {
            case BURN, POISON, PARALYZE -> super.calcBaseDamage(pokemon, pokemon1) * 2;
            default -> super.calcBaseDamage(pokemon, pokemon1);
        };

    }

    @Override
    protected String describe() {
        return "использует 'Facade'";
    }
}
