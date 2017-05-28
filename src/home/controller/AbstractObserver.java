package home.controller;

import java.util.Set;

import home.view.MessageType;
import home.view.View;
//a skeleton of an implementation of observer
abstract class AbstractObserver {
    protected abstract Set<? extends View<?>> getAttached();
    protected void update() {
        this.getAttached().forEach(x -> {
            this.defineUpdateView(x);
            x.show();
        });
    }
    protected abstract void defineUpdateView(View<?> view);
    protected final void showMessageInViews(final String message, final MessageType type) {
        this.getAttached().forEach(x -> x.showMessage(message, type));
    }
}
