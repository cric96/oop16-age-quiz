package home.view;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import home.view.menu.GameMenu;
import home.view.menu.MainMenu;
import home.view.menu.MainMenuImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



/**
 * 
 * View implementation of MainView.
 *
 */
public class MainView extends Application {
    private static final MainMenu MAIN_MENU = new MainMenuImpl();
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    private Stage primaryStage;
    private ImageView imgView;
    private Pane root;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle(MAIN_MENU.getTitle());
        root = new Pane();
        root.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        InputStream is = Files.newInputStream(Paths.get(MAIN_MENU.backgroundPath()));
        Image img = new Image(is);
        is.close();
        imgView = new ImageView(img);
        imgView.setFitWidth(WINDOW_WIDTH);
        imgView.setFitHeight(WINDOW_HEIGHT);
        GameMenu gameMenu = new GameMenu(this);

        root.getChildren().addAll(imgView, gameMenu);
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setScene(final Scene scene) {
        primaryStage.setScene(scene);
    }

    public static void main(String[] args){
        launch(args);
    }

 }

