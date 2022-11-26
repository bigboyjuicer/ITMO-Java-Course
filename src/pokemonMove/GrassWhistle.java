package pokemonMove;

import ru.ifmo.se.pokemon.*;

public class GrassWhistle extends StatusMove {

    public GrassWhistle() {
        super(Type.GRASS, 0, 0.55);
    }


    @Override
    protected void applyOppEffects(Pokemon pokemon) {

        Effect.sleep(pokemon);

    }

    @Override
    protected String describe() {
        return "использует 'Grass Whistle'";
    }
}
