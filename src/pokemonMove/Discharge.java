package pokemonMove;

import ru.ifmo.se.pokemon.*;

public class Discharge extends SpecialMove {

    public Discharge() {
        super(Type.ELECTRIC, 80, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {

        if(Math.random() <= 0.3) {
            Effect.paralyze(pokemon);
        }

    }

    @Override
    protected String describe() {
        return "использует 'Discharge'";
    }
}
