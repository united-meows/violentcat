package pisi.unitedmeows.violentcat.shared.action;


import pisi.unitedmeows.yystal.utils.Stopwatch;
import pisi.unitedmeows.yystal.utils.kThread;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Action<Result> implements Runnable {

    static final int DEFAULT_TIMEOUT = 10000;

    protected Stopwatch stopwatch;
    protected long took;
    protected boolean finished;
    protected Result result;
    protected String victim;
    protected List<Consumer<Result>> postTasks;

    public Action() {
        this("");
    }

    public Action(String _victim) {
        victim = _victim;
        postTasks = new ArrayList<>(0);
        stopwatch = new Stopwatch();
    }

    @OnlyLibCalls
    @Deprecated
    public void end(Result _result) {
        result = _result;
        took = stopwatch.elapsed();
        finished = true;
        stopwatch.reset();
    }

    public Result await() {
        while (!finished) {

            kThread.sleep(3);
        }
        return result;
    }


    public Action<Result> then(Consumer<Result> result) {
        postTasks.add(result);
        return this;
    }
}
