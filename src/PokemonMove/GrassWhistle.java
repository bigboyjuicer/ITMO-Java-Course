package PokemonMove;

import ru.ifmo.se.pokemon.*;

public class GrassWhistle extends StatusMove {

    public GrassWhistle() {
        super(Type.GRASS, 0, 0.55);
    }


    /**
     * TODO: Find how sleep works
     * @param pokemon
     */
    @Override
    protected void applyOppEffects(Pokemon pokemon) {

        pokemon.setCondition(new Effect().condition(Status.SLEEP));

    }

    @Override
    protected String describe() {
        return "использует 'Grass Whistle'";
    }
}
