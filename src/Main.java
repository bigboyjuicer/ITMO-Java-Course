import entity.*;
import exceptions.GenderException;
import exceptions.TemperatureException;
import types.*;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        Parent mother;
        Parent father;
        try {
            mother = new Parent(Genders.WOMAN, FamilyRoles.MOTHER);
            father = new Parent(Genders.MAN, FamilyRoles.FATHER);
        } catch (GenderException e) {
            throw new RuntimeException(e);
        }
        Person bosse = new Person("Bosse", Genders.MAN);
        Baby baby = new Baby();
        Person carlson = new Person("Carlson", Genders.MAN) {
            @Override
            public void move(Places place) {
                System.out.println(getName() + " flew to " + place.getName());
            }
        };
        Group conversation = new Group(new Person("Betan", Genders.WOMAN), new Person("Pelle", Genders.MAN), Places.CANTEEN, GroupTypes.CONVERSATION);
        Group fight = new Group(new Person("Boy", Genders.MAN), new Person("Boy", Genders.MAN), Places.STREET, GroupTypes.FIGHT);
        TimeOfAction evening = new TimeOfAction(DayTime.EVENING);
        initTemperature(15);
        TimeOfAction.TemperatureManager temperatureManager = new TimeOfAction.TemperatureManager();
        Tree lipa = new Tree("Lipa");
        initLeaves(lipa);


        System.out.println(evening.getDayTime().getName());
        temperatureManager.calculateWeatherType();
        lipa.hasLeaves();

        mother.move(Places.CINEMA);
        father.move(Places.CINEMA);
        bosse.move(Places.STADIUM);
        baby.action(Actions.SIT, ThingsActedUpon.NONE, true);
        baby.action(Actions.OPEN, ThingsActedUpon.DOOR, true);
        baby.observe(conversation);
        baby.action(Actions.UNDERSTAND, ThingsActedUpon.WHAT_WAS_SAID, true);
        baby.action(Actions.COME, ThingsActedUpon.WINDOW, true);
        baby.action(Actions.LOOK, ThingsActedUpon.TWILIGHT, true);
        baby.action(Actions.LOOK, ThingsActedUpon.STREET, true);
        baby.observe(fight);
        baby.action(Actions.SPECTATE, ThingsActedUpon.THEM, true);
        fight.setOver(true);

        carlson.move(Places.FLAT);


    }

    public static void initTemperature(double temperature) {
        if(temperature <= -200.0 || temperature >= 200.0) {
            throw new TemperatureException();
        }

        TimeOfAction.setTemperature(temperature);
    }

    public static void initLeaves(Tree tree) {
        List<Tree.Leaf> leaves = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            leaves.add(tree.new Leaf(Sizes.SMALL, Colors.GREEN, Properties.STICKY));
        }
        tree.setLeaves(leaves);
    }

}