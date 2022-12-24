package entity;

import interfaces.Actionable;
import interfaces.Observable;
import types.*;

public class Baby extends Person implements Actionable, Observable {

    public Baby() {
        super("Baby", Genders.MAN);
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
