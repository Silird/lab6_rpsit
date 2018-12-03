package ru.SilirdCo.Lab6.Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.Lab6.Main.Frames.ClientFrameController;
import ru.SilirdCo.Lab6.Main.Frames.EmptyPanel;
import ru.SilirdCo.Lab6.Util.ExceptionHandler;

import java.io.IOException;

public class Client extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ClientFrameController controller = new ClientFrameController();
        Node content;

        FXMLLoader loader = new FXMLLoader(Server.class.getResource("/Frames/ClientPanel.fxml"));
        loader.setController(controller);

        try {
            content = loader.load();
        }
        catch (IOException ex) {
            content = EmptyPanel.get();
            ExceptionHandler.handle(logger, ex);
        }

        primaryStage = new Stage();
        Scene scene = new Scene((Parent) content);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Клиент");
        primaryStage.show();

        primaryStage.setOnCloseRequest(we -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
