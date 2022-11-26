package pokemonEntity;

import pokemonMove.ChargeBeam;
import pokemonMove.Discharge;
import pokemonMove.Facade;
import pokemonMove.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Plusle extends Pokemon {

    public Plusle(String name, int level) {
        super(name, level);


        setStats(60, 50, 40, 85, 75, 95);
        setType(Type.ELECTRIC);
        setMove(new ChargeBeam(), new Facade(), new Swagger(), new Discharge());
    }



}