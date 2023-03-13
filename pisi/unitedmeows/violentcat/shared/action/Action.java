package pisi.unitedmeows.violentcat.shared.action;


import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.utils.Stopwatch;
import pisi.unitedmeows.yystal.utils.kThread;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Action<Result> implements Runnable {

    public static Action<?> BLANK_ACTION = new Action<Object>() {
        @Override
        public void run() {}
    };

    static {
        BLANK_ACTION.result = null;
        BLANK_ACTION.finished = true;
    }

    public static final int TASK_COMPLETED = 1;
    public static final int TASK_FAILED = -1;
    public static final int TASK_UNKNOWN_RESULT = -1;
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
            kThread.sleep(YYStal.setting(YSettings.TASK_AWAIT_DELAY));
        }
        return result;
    }


    public Action<Result> then(Consumer<Result> result) {
        postTasks.add(result);
        return this;
    }

    public static <X> Action<X> completedTask(final Object obj) {
        return new Action() {
            @Override
            public void run() {
                end(obj);
            }
        };
    }
}
