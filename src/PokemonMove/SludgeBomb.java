package PokemonMove;

import ru.ifmo.se.pokemon.*;

public class SludgeBomb extends SpecialMove {

    public SludgeBomb() {
        super(Type.POISON, 90, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if(!pokemon.hasType(Type.POISON) || !pokemon.hasType(Type.STEEL) || Math.random() <= 0.3) {
            pokemon.setCondition(new Effect().condition(Status.POISON));
        }
    }

    @Override
    protected String describe() {
        return "использует 'Sludge Bomb'";
    }
}
