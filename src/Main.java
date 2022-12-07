import entity.*;
import types.*;

public class Main {


    public static void main(String[] args) {

        Parent mother = new Parent(FamilyRoles.MOTHER);
        Parent father = new Parent(FamilyRoles.FATHER);
        Person bosse = new Person("Bosse", Genders.MAN);
        Baby baby = new Baby();
        Group conversation = new Group(new Person("Betan", Genders.WOMAN), new Person("Pelle", Genders.MAN), Places.CANTEEN, GroupTypes.CONVERSATION);
        Group fight = new Group(new Person("Boy", Genders.MAN), new Person("Boy", Genders.MAN), Places.STREET, GroupTypes.FIGHT);

        mother.move(Places.CINEMA);
        father.move(Places.CINEMA);
        bosse.move(Places.STADIUM);
        baby.action(Actions.SIT, ThingsActedUpon.NONE, true);
        baby.action(Actions.OPEN, ThingsActedUpon.DOOR, true);
        baby.observe(conversation);
        baby.action(Actions.UNDERSTAND, ThingsActedUpon.WHAT_WAS_SAID, false);
        baby.action(Actions.COME, ThingsActedUpon.WINDOW, true);
        baby.action(Actions.LOOK, ThingsActedUpon.TWILIGHT, true);
        baby.action(Actions.LOOK, ThingsActedUpon.STREET, true);
        baby.observe(fight);
        baby.action(Actions.SPECTATE, ThingsActedUpon.THEM, true);
        fight.setOver(true);


    }
}