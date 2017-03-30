package home.view.menu;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * View implementation of MenuView.
 *
 */
public class Menu extends Application {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        try {
            InputStream is = Files.newInputStream(Paths.get("images/backgroundMenu.jpg"));
            Image img = new Image(is);
            is.close();
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(WINDOW_WIDTH);
            imgView.setFitHeight(WINDOW_HEIGHT);
            GameMenu gameMenu = new GameMenu();
            root.getChildren().addAll(imgView, gameMenu);
        } catch (Exception e) {
            System.out.println("Errore caricamento sfondo ");
        }
       
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * 
     * @param args arg.
     */
    public static void main(final String[] args) {
        launch(args);
    }
 }
