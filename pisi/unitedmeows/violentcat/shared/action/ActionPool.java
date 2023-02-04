package pisi.unitedmeows.violentcat.shared.action;

import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Promise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionPool<ActionTypes> {

    private Map<ActionTypes,  RateListener> rateListenerMap;
    private Promise loopPromise;

    ActionPool() {
        rateListenerMap = new HashMap<>();
    }

    public void start() {
        loopPromise = Async.async_loop(this::tick, 25);
    }

    public void tick() {
        for (RateListener listener : rateListenerMap.values())
            listener.tick();
    }


}
