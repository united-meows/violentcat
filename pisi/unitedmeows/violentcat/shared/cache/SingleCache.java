package pisi.unitedmeows.violentcat.shared.cache;

import pisi.unitedmeows.yystal.utils.Stopwatch;

public class SingleCache<X>  {

    private X value;
    private Stopwatch stopwatch;
    private int timeout;

    public SingleCache(int _timeout) {
        stopwatch = new Stopwatch();
        timeout = _timeout;
    }

    public boolean shouldUpdate() {
        return stopwatch.elapsed() > timeout;
    }

    public SingleCache<X> value(X _value) {
        value = _value;
        return this;
    }

    public X value() {
        return value;
    }
}
