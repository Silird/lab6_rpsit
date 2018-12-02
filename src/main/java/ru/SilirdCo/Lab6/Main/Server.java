package ru.SilirdCo.Lab6.Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.Lab6.Util.ExceptionHandler;

import java.io.IOException;
import java.sql.*;

public class Server extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    private final static String URL = "jdbc:sqlite:C:\\Work\\SQLLite\\data.db";
    private final String SERVER_TITLE = "Server";
    private final static String SQL_SELECT_SERVERS = "SELECT * from FusionServers";
    private final static String TAB_SERVERS = "Fusion Servers";
    private TableView tableViewServers;
    private ObservableList<ObservableList> data;

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
    private void initialize(Stage stage) {
        stage.setTitle(SERVER_TITLE);
        Group root = new Group();
        Scene scene = new Scene(root);
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        BorderPane mainPane = new BorderPane();
        Tab tabServers = new Tab();
        tabServers.setText(TAB_SERVERS);
        VBox vBoxServers = new VBox();
        tableViewServers = new TableView();
        buildData(SQL_SELECT_SERVERS, tableViewServers);
        vBoxServers.getChildren().add(tableViewServers);
        tabServers.setContent(vBoxServers);
        tabPane.getTabs().add(tabServers);
        mainPane.setCenter(tabPane);
        mainPane.prefHeightProperty().bind(scene.heightProperty());
        mainPane.prefWidthProperty().bind(scene.widthProperty());
        root.getChildren().add(mainPane);
        stage.setScene(scene);
        stage.show();
    }
    */



    public static void start() {
        Platform.runLater(() -> {
            ServerFrameController controller = new ServerFrameController();
            Node mainFrameNode;

            FXMLLoader loader = new FXMLLoader(Server.class.getResource("/Frame"));
            loader.setController(controller);

            Node content = null;
            try {
                content = loader.load();
            }
            catch (IOException ex) {
                content = EmptyPanel.get();
                ExceptionHandler.handle(logger, ex);
            }

            Stage stage = new Stage();
            Scene scene = new Scene((Parent) mainFrameNode);
            stage.setScene(scene);
            stage.setTitle("Главное меню");
            stage.show();
            stage.setMaximized(true);

            stage.setOnCloseRequest(we -> {
                Platform.exit();
                System.exit(0);
            });
        });
    }

    public void buildData(String sql, TableView tableView) {
        Connection c;
        data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            c = connect();
            rs = c.createStatement().executeQuery(sql);
            for (int i=0; i<rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().
                        getColumnName(i+1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                tableView.getColumns().addAll(col);
            }
            while(rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableView.setItems(data);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public String selectProcessors(String model){
        String sql = "select Processors from FusionServers where Model=\""+ model
                + "\"";
        String processors = null;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                processors = rs.getString("Processors");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return processors;
    }

    @Override
    public void start(Stage stage) {
        initialize(stage);
    }
}
