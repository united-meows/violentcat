package pisi.unitedmeows.violentcat.utils;

import java.util.Date;

public class Timestamp {

    private long time;
    private Type type = Type.RELATIVE;

    protected Timestamp(long _time) {
        time = _time / 1000;
    }

    protected Timestamp(long _time, Type _type) {
        this(_time);
        type = _type;
    }

    protected Timestamp(Date _date) {
        this(_date.getTime());
    }

    protected Timestamp(Date _date, Type _type) {
        this(_date);
        type = _type;
    }

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp from(long _time, Type _type) {
        return new Timestamp(_time, _type);
    }

    public static Timestamp from(Date _date) {
        return new Timestamp(_date);
    }

    public static Timestamp from(Date _date, Type _type) {
        return new Timestamp(_date, _type);
    }

    public static Timestamp from(long _time) {
        return new Timestamp(_time);
    }

    public Timestamp addMilliseconds(long ms) {
        addSeconds(ms / 1000);
        return this;
    }

    public Timestamp addSeconds(long seconds) {
        time += seconds;
        return this;
    }

    public Timestamp addDays(long days) {
        addSeconds(days * 24 * 60 * 60);
        return this;
    }

    public Timestamp addYear(long year) {
        addDays(year * 365);
        return this;
    }

    protected Timestamp type(Type _type) {
        type = _type;
        return this;
    }

    @Override
    public String toString() {
        return "<t:" + time + ":" + type().code + ">";
    }

    public Type type() {
        return type;
    }

    public static enum Type {

        SHORT_TIME("t"),
        SHORT_DATE("d"),
        LONG_TIME("T"),
        LONG_DATE("D"),
        LONG_DATETIME("f"),
        LONG_FULL("F"),
        RELATIVE("R");

        String code;
        Type(String _code) {
            code = _code;
        }

        public String code() {
            return code;
        }
    }
}
