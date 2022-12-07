package interfaces;

import types.Actions;
import types.ThingsActedUpon;

public interface Actionable {
    void action(Actions action, ThingsActedUpon thing, boolean isSucceed);
}
