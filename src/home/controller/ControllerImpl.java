package home.controller;

import java.util.Set;

import home.view.View;

class ControllerImpl implements Controller {
    private final AbstractObserver observer;
    ControllerImpl(final AbstractObserver observer) {
        this.observer = observer;
    }
    @Override
    public Set<? extends View<?>> getViews() {
        return this.observer.getAttached();
    }

    @Override
    public void checkUpdate() {
        this.observer.update();
    }

}
