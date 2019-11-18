package View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private Pane root = new Pane();

    private VBox menuBox = new VBox();

    private Button button;

    private Text title;

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        stage.setTitle("Civilization VI Menu");
        stage.setScene(scene);
        stage.show();

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // stage.setScene();
                System.out.println("test");
            }
        });

    }

    private Parent createContent() {

        root.setPrefWidth(WIDTH);
        root.setPrefHeight(HEIGHT);

        addTitle();
        addButton();

        return root;
    }

    private void addButton() {
        button = new Button("Play Game");
        button.setTranslateX(WIDTH / 2 - button.getLayoutBounds().getWidth() / 2 - 50);
        button.setTranslateY(HEIGHT / 3 + title.getLayoutBounds().getHeight());
        root.getChildren().add(button);
    }

    private void addTitle() {
        title = new Text("CIVILIZATION VI");

        title.setFill(Color.BLACK);
        //title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        //title.setTranslateY(HEIGHT / 3);

        title.setTranslateX(WIDTH / 2 - title.getLayoutBounds().getWidth() / 2);

        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
