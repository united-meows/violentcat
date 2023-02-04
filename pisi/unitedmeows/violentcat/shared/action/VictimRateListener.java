package pisi.unitedmeows.violentcat.shared.action;

import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.Stopwatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class VictimRateListener<MajorType extends Enum<?>> extends RateListener<MajorType> {

    public VictimRateListener(MajorType _type, int _maxRate, int _resetInterval) {
        super(_type, _maxRate, _resetInterval);
    }

    private Map<String, VictimRate> victimMap = new HashMap<>();

    @Override
    public void tick() {
        victimMap.entrySet().removeIf((e) -> !e.getValue().isRateLimited() && e.getValue().actionQueue.isEmpty());
        victimMap.forEach((key, value) -> { if (value.stopwatch.isReached(resetInterval)) { value.rate = 0; }}); // TODO

        for (VictimRate victim : victimMap.values()) {
            if (victim.isRateLimited())  continue;

            Action<?> action = victim.poll();
            if (action != null) {
                victim.rate++;
                Async.async(action);
            }
        }
    }

    @Override
    public void queue(Action<?> action) {
        if (action.victim.isEmpty()) {
            VictimRate rate = victimMap.computeIfAbsent(action.victim, (k) -> new VictimRate(this));
            rate.queue(action);
        }
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
            return rate >= maxRate || !stopwatch.isReached(owner.resetInterval);
        }

        public void queue(Action<?> action) {
            actionQueue.add(action);
        }

        public Action<?> poll() {
            return actionQueue.poll();
        }


    }
}