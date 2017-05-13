package home.view.debug;

import java.util.Objects;
import java.util.Optional;

import home.controller.observer.Observer;
import home.view.MessageType;
import home.view.View;

abstract class AbstractConsoleView<E extends Observer> implements View<E> {
    private E controller;
    @Override
    public final void attachOn(final E controller) {
        Objects.requireNonNull(controller);
        this.controller = controller;
    }

    @Override
    public final void showMessage(final String message, final MessageType messageType) {
        System.out.println(messageType.toString() + " : -> " + message);
    }

    protected Optional<E> getController() {
        return Optional.ofNullable(controller);
    }

}
