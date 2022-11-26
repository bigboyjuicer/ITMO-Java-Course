package pokemonEntity;

import pokemonMove.Facade;
import pokemonMove.SludgeBomb;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Bellsprout extends Pokemon {

    public Bellsprout(String name, int level) {

        super(name, level);

        setType(Type.GRASS, Type.POISON);
        setStats(50, 75, 35, 70, 30, 40);
        setMove(new Facade(), new SludgeBomb());
    }

}
