package pisi.unitedmeows.violentcat.shared.action;

import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.Stopwatch;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RateListener<ActionType extends java.lang.Enum<?>> {

    protected int resetInterval;
    private ActionType type;
    protected int maxRate;
    protected int currentRate;
    protected List<Action<?>> actionList;
    private Stopwatch stopwatch;

    public RateListener(ActionType _type, int _maxRate, int _resetInterval) {
        type = _type;
        resetInterval = _resetInterval;
        maxRate = _maxRate;
        actionList = new CopyOnWriteArrayList<>();
    }

    public void queue(Action<?> action) {
        actionList.add(action);
    }

    public void tick() {
        if (stopwatch.isReached(resetInterval)) {
            stopwatch.reset();
            currentRate = 0;
        }

        for (Action<?> action : actionList) {
            if (isRateLimited())
                break;
            Async.async(action);
            currentRate++;
        }
    }

    private boolean isRateLimited() {
        return currentRate >= maxRate;
    }
}
