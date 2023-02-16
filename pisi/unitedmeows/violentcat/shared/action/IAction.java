package pisi.unitedmeows.violentcat.shared.action;

@FunctionalInterface
public interface IAction<X> {
    X run();
}
