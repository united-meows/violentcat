package pisi.unitedmeows.violentcat.shared.action;

import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Promise;
import pisi.unitedmeows.yystal.utils.Stopwatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ActionPool<ActionTypes> {

    protected Map<ActionTypes,  RateListener> rateListenerMap;
    private Promise loopPromise;

    /* global limits */
    protected int globalLimit = 50;
    protected int currentRequestCount;
    private Stopwatch globalLimitListener = new Stopwatch();


    public ActionPool() {
        rateListenerMap = new HashMap<>();
    }

    @OnlyLibCalls
    protected void registerRateListener(ActionTypes type, RateListener listener) {
        rateListenerMap.put(type, listener);
    }

    public RateListener rateListener(ActionTypes type) {
        return rateListenerMap.getOrDefault(type, null);
    }

    @OnlyLibCalls
    public void start() {
        loopPromise = Async.async_loop(this::tick, 25);
    }

    @OnlyLibCalls
    public void tick() {
        for (RateListener listener : rateListenerMap.values())
            listener.tick();

        if (globalLimitListener.isReached(1000 /* DISCORD RESETS GLOBAL LIMIT PER SECOND */)) {
            if (currentRequestCount >= globalLimit) {
                // TODO: USE LOGGER
                System.out.println("YOU ARE EXCEEDING THE GLOBAL DISCORD LIMIT");
            }

            currentRequestCount = 0;
            globalLimitListener.reset();
        }
    }


}
