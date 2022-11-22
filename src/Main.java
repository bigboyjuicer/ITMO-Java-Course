import PokemonEntity.*;
import ru.ifmo.se.pokemon.Battle;

public class Main {

    public static void main(String[] args) {

        Plusle plusle = new Plusle("", 100);
        Sunkern sunkern = new Sunkern("", 100);
        Sunflora sunflora = new Sunflora("", 100);
        Bellsprout bellsprout = new Bellsprout("", 100);
        Weepinbell weepinbell = new Weepinbell("", 100);
        Victreebel victreebel = new Victreebel("", 100);

        Battle b = new Battle();

        b.addAlly(plusle);
        b.addAlly(sunkern);
        b.addAlly(sunflora);
        b.addAlly(bellsprout);
        b.addAlly(weepinbell);
        b.addFoe(victreebel);

        b.go();


    }


}
