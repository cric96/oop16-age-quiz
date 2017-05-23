package home.view.fx.buttons;

import java.util.HashSet;
import java.util.Set;

import home.controller.observer.MenuObserver;
import home.utility.BundleLanguageManager;
import home.utility.view.FontManager;
import home.view.fx.parents.ParentMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
/**
 * class that represent a horizontal box used to display buttons to select language in the main menu.
 */
class LanguageBox extends HBox {
    LanguageBox(final ParentMenu parent, final MenuObserver menu) {
        final String selectedButtonEffect = "-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0.6); -fx-background-radius: 0";
        final String notSelectedButtonEffect = "-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0); -fx-background-radius: 0";
        final int fontSize = 25;
        final Set<Button> languageButtonSet = new HashSet<>();
        this.setAlignment(Pos.BOTTOM_RIGHT);
        BundleLanguageManager.get().getSupportedLanguage().forEach(language -> {
            final Button langButton = new Button(language.toString());

            langButton.setFont(FontManager.getGeneralFont(fontSize));
            if (language.equals(BundleLanguageManager.get().getCurrentLocale())) {
                langButton.setStyle(selectedButtonEffect);
            } else {
                langButton.setStyle(notSelectedButtonEffect);
            }
            languageButtonSet.add(langButton);
            langButton.setOnMouseClicked(e -> {
                menu.changeLocale(language);
                parent.repaint();
                langButton.setStyle(selectedButtonEffect);
                languageButtonSet.stream().filter(button -> !button.equals(langButton))
                                          .forEach(button -> {
                                              button.setStyle(notSelectedButtonEffect);
                                          });
            });
            this.getChildren().add(langButton);
       });
    }
}
