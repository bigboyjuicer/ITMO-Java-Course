package pokemonEntity;

import pokemonMove.LeafStorm;

public class Sunflora extends Sunkern{

    public Sunflora(String name, int level) {

        super(name, level);

        setStats(75, 75, 55, 105, 85, 30);
        addMove(new LeafStorm());
    }


}
