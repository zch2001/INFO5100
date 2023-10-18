import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloFX extends Application {

    private StackPane root = new StackPane();

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Say Hello World");
        btn.setOnAction(e -> {
            try {
                System.out.println("Hello World");
                Parent pane = (Parent) FXMLLoader.load(getClass().getResource("HelloL.fxml")) ;
                primaryStage.getScene().setRoot(pane);
            }catch (IOException ex){
                Logger.getLogger(HelloFX.class.getName()).log(Level.SEVERE,null,ex);
            }



//            // Switch to 'hello' View
//            switchToHelloView();
        });

        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("HelloFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//
//    private void switchToHelloView() {
//        Label helloLabel = new Label("Hello");
//        root.getChildren().clear();
//        root.getChildren().add(helloLabel);
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
