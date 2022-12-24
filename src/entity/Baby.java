package entity;

import interfaces.Actionable;
import interfaces.Observable;
import types.*;

import java.util.*;

public class Baby extends Person implements Actionable, Observable {

    public Baby() {
        super("Baby", Genders.MAN);
    }


    public void searchInBox() {

        class Box {
            List<Stuff> stuff;

            public Box(List<Stuff> stuff) {
                this.stuff = stuff;
            }

            public List<Stuff> getStuff() {
                return stuff;
            }

            public void setStuff(List<Stuff> stuff) {
                this.stuff = stuff;
            }
        }

        List<Stuff> allStuff = Arrays.asList(Stuff.values());
        Collections.shuffle(allStuff);
        allStuff = allStuff.subList(0, 3);

        Box box = new Box(allStuff);

        System.out.print(getName() + " is rummaging in the box and finding: ");
        for(Stuff stuff: allStuff) {
            System.out.print(stuff.getName() + " ");
        }

    }

    @Override
    public void action(Actions action, ThingsActedUpon thing, boolean isSucceed) {
        switch (action) {
            case SIT:
                System.out.println(this.getName() + " " + action.getName() + "s");
                break;
            case OPEN:
                System.out.println(this.getName() + " " + action.getName() + " the " + thing.getName());
                break;
            case UNDERSTAND:
                if (isSucceed) System.out.println(this.getName() + " " + action.getName() + " " + thing.getName());
                else System.out.println(this.getName() + " tried to " + action.getName() + " " + thing.getName() + " but didn't succeed");
                break;
            case COME:
                System.out.println(this.getName() + " " + action.getName() + " to a " + thing.getName());
                break;
            case LOOK:
                System.out.println(this.getName() + " " + action.getName() + " at " + thing.getName());
                break;
            case SPECTATE:
                System.out.println(this.getName() + " " + action.getName() + " " + thing.getName());
                break;
        }
    }

    @Override
    public void observe(Group group) {
        System.out.println(this.getName() + " is observing " + group.getType().getName() + " from " + group.getPlace().getName() + " between " + group.getPerson1().getName() + " and " + group.getPerson2().getName());
    }

    @Override
    public String toString() {
        return "Baby{" +
                "gender=" + getGender() +
                "place=" + getPlace() +
                "name=" + getName() +
                '}';
    }
}
