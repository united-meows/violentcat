package pisi.unitedmeows.violentcat.shared.action;

import pisi.unitedmeows.violentcat.bot.action.BotActionPool;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.Stopwatch;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class RateListener<ActionType extends java.lang.Enum<?>> {
    protected int resetInterval;
    private ActionType type;
    protected int maxRate;
    protected int currentRate;
    protected Queue<Action<?>> actionQueue;
    private Stopwatch stopwatch;
    private ActionPool owner;

    public RateListener(ActionPool _owner, ActionType _type, int _maxRate, int _resetInterval) {
        owner = _owner;
        type = _type;
        resetInterval = _resetInterval;
        maxRate = _maxRate;
        actionQueue = new ArrayDeque<>();
        stopwatch = new Stopwatch();
    }

    public Action<?> queue(IAction<?> function) {
        final Action<?> action = new Action<>() {
            @Override
            public void run() {
                end(function.run());
                increaseRequestCount();
            }
        };

        actionQueue.add(action);
        return action;
    }

    public Action<?> queue(String victim, IAction<?> function) {
        final Action<?> action = new Action<>(victim) {
            @Override
            public void run() {
                end(function.run());
                increaseRequestCount();
            }
        };

        actionQueue.add(action);
        return action;
    }

    protected void increaseRequestCount() {
        owner.currentRequestCount++;
    }

    public void tick() {
        if (stopwatch.isReached(resetInterval)) {
            stopwatch.reset();
            currentRate = 0;
        }

        while (!actionQueue.isEmpty()) {
            if (isRateLimited())
                break;
            Async.async(actionQueue.poll());
            currentRate++;
        }
    }

    private boolean isRateLimited() {
        return currentRate >= maxRate;
    }
}
