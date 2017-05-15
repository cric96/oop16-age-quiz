package home.controller;

import java.util.Set;

import home.view.MessageType;
import home.view.View;
//a skeleton of an implementation of observer
abstract class AbstractObserver {
    protected abstract Set<? extends View<?>> getAttached();
    protected void update() {
        this.getAttached().forEach(x -> {
            x.show();
            this.defineUpdateView(x);
        });
    }
    protected abstract void defineUpdateView(View<?> view);
    protected final void showErrors(final String message) {
        this.getAttached().forEach(x -> x.showMessage(message, MessageType.ERROR));
    }
}
