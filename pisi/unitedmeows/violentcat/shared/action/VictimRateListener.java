package pisi.unitedmeows.violentcat.shared.action;

import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.Stopwatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class VictimRateListener<MajorType extends Enum<?>> extends RateListener<MajorType> {

    public VictimRateListener(ActionPool _owner, MajorType _type, int _maxRate, int _resetInterval) {
        super(_owner, _type, _maxRate, _resetInterval);
    }

    private Map<String, VictimRate> victimMap = new HashMap<>();

    @Override
    public void tick() {
        victimMap.entrySet().removeIf((e) -> !e.getValue().isRateLimited() && e.getValue().actionQueue.isEmpty());

        // TODO there was a problem but i don't remember now
        victimMap.forEach((key, value) -> { if (value.stopwatch.isReached(resetInterval)) { value.rate = 0; value.stopwatch.reset(); }});


        for (VictimRate victim : victimMap.values()) {
            if (victim.isRateLimited()) {
                continue;
            }

            Action<?> action = victim.poll();
            if (action != null) {
                victim.rate++;
                Async.async(action);
            }
        }
    }

    @Override
    public Action<?> queue(String victim, IAction<?> functionalAction) {
        Action<?> action = new Action<>(victim) {
            @Override
            public void run() {
                end(functionalAction.run());
                increaseRequestCount();
            }
        };
        if (!action.victim.isEmpty()) {
            VictimRate rate = victimMap.computeIfAbsent(action.victim, (k) -> new VictimRate(this));
            rate.queue(action);
            return action;
        }
        return Action.BLANK_ACTION;
    }

    @Override
    public Action<?> queue(IAction<?> action) {
        // do nothing
        return Action.BLANK_ACTION;
    }

    @OnlyLibCalls
    public class VictimRate {
        int rate;
        Stopwatch stopwatch;
        LinkedBlockingQueue<Action<?>> actionQueue;
        VictimRateListener owner;

        public VictimRate(VictimRateListener _owner) {
            owner = _owner;
            rate = 0;
            stopwatch = new Stopwatch();
            actionQueue = new LinkedBlockingQueue<>();
        }

        boolean isRateLimited() {
            return rate >= maxRate;
        }

        public void queue(Action<?> action) {
            actionQueue.add(action);
        }

        public Action<?> poll() {
            return actionQueue.poll();
        }


    }
}