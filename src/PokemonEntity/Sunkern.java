package PokemonEntity;

import PokemonMove.DoubleTeam;
import PokemonMove.GrassWhistle;
import PokemonMove.RazorLeaf;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Sunkern extends Pokemon {

    public Sunkern(String name, int level) {
        super(name, level);

        setStats(30, 30, 30, 30, 30, 30);
        setType(Type.GRASS);
        setMove(new DoubleTeam(), new RazorLeaf(), new GrassWhistle());
    }

}
