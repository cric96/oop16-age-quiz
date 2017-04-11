package home.view;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import home.view.menu.GameMenu;
import home.view.menu.MainMenu;
import home.view.menu.MainMenuImpl;
import home.view.menu.MenuViewImpl;
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
    private static Stage primaryStage;
    private ImageView imgView;
    private Pane root;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        MainView.primaryStage = primaryStage;
        final Pane root = new Pane();
        final InputStream is = Files.newInputStream(Paths.get(MAIN_MENU.backgroundPath()));
        final Image img = new Image(is);
        is.close();

        primaryStage.setTitle(MAIN_MENU.getTitle());
        //root.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        imgView = new ImageView(img);
        imgView.setFitWidth(WINDOW_WIDTH);
        imgView.setFitHeight(WINDOW_HEIGHT);
        //final GameMenu gameMenu = new GameMenu();
        //final Scene scene = new Scene(root);

        //root.getChildren().addAll(imgView, gameMenu);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new MenuViewImpl());
        primaryStage.show();
        FXContainer.getContainer().setStage(primaryStage);
    }

    public static void setScene(final Scene scene) {
        primaryStage.setScene(scene);
    }

    public static void main(String[] args){
        launch(args);
    }

 }

