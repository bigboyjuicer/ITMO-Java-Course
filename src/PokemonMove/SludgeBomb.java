package PokemonMove;

import ru.ifmo.se.pokemon.*;

public class SludgeBomb extends SpecialMove {

    public SludgeBomb() {
        super(Type.POISON, 90, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect.poison(pokemon);
    }

    @Override
    protected String describe() {
        return "использует 'Sludge Bomb'";
    }
}
