package PokemonMove;

import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove {

    public DoubleTeam() {
        super(Type.NORMAL, 0, 1);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {

        pokemon.setMod(Stat.SPEED, 1);

    }

    @Override
    protected String describe() {
        return "использует 'Double Team'";
    }
}
