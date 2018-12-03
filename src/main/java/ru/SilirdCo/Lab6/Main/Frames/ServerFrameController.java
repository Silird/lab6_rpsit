package ru.SilirdCo.Lab6.Main.Frames;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.Lab6.Main.DAO.DAOServer;
import ru.SilirdCo.Lab6.Main.DAO.IDAO;
import ru.SilirdCo.Lab6.Main.Entities.Department;
import ru.SilirdCo.Lab6.Main.Entities.Employer;
import ru.SilirdCo.Lab6.Main.Entities.IpPhone;
import ru.SilirdCo.Lab6.Main.Services.ServerService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class ServerFrameController implements Initializable {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ServerFrameController.class);

    @FXML
    private Button butUpdateIpPhones;
    @FXML
    private TableView<IpPhone> tableIpPhones;

    @FXML
    private Button butUpdateDepartments;
    @FXML
    private TableView<Department> tableDepartments;

    @FXML
    private Button butUpdateEmployers;
    @FXML
    private TableView<Employer> tableEmployers;

    private final IDAO dao = new DAOServer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTables();
        initListeners();
        updateTables();

        CompletableFuture.runAsync(ServerService::start);
    }

    @SuppressWarnings("unchecked")
    private void initTables() {
        for (int i = 0; i < 3; i++) {
            tableIpPhones.getColumns().add(new TableColumn<>());
        }

        TableColumn column;

        column = tableIpPhones.getColumns().get(0);
        column.setText("Идентификатор");
        column.setCellValueFactory(new PropertyValueFactory<IpPhone, Integer>("id"));
        column = tableIpPhones.getColumns().get(1);
        column.setText("Название");
        column.setCellValueFactory(new PropertyValueFactory<IpPhone, String>("name"));
        column = tableIpPhones.getColumns().get(2);
        column.setText("Номер");
        column.setCellValueFactory(new PropertyValueFactory<IpPhone, String>("number"));


        for (int i = 0; i < 3; i++) {
            tableDepartments.getColumns().add(new TableColumn<>());
        }

        column = tableDepartments.getColumns().get(0);
        column.setText("Идентификатор");
        column.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
        column = tableDepartments.getColumns().get(1);
        column.setText("Название");
        column.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
        column = tableDepartments.getColumns().get(2);
        column.setText("Номер");
        column.setCellValueFactory(new PropertyValueFactory<Department, String>("number"));


        for (int i = 0; i < 4; i++) {
            tableEmployers.getColumns().add(new TableColumn<>());
        }

        column = tableEmployers.getColumns().get(0);
        column.setText("Идентификатор");
        column.setCellValueFactory(new PropertyValueFactory<Employer, Integer>("id"));
        column = tableEmployers.getColumns().get(1);
        column.setText("Название");
        column.setCellValueFactory(new PropertyValueFactory<Employer, String>("name"));
        column = tableEmployers.getColumns().get(2);
        column.setText("Отдел");
        column.setCellValueFactory(new PropertyValueFactory<Employer, Department>("department"));
        column = tableEmployers.getColumns().get(3);
        column.setText("IP телефон");
        column.setCellValueFactory(new PropertyValueFactory<Employer, IpPhone>("ipPhone"));
    }

    private void updateTables() {
        updateIpPhones(dao.getIpPhones());
        updateDepartments(dao.getDepartments());
        updateEmployers(dao.getEmployers());
    }

    private void updateIpPhones(List<IpPhone> entities) {
        Platform.runLater(() -> {
            // Очистка таблицы
            tableIpPhones.getItems().clear();

            // Заполнение таблицы
            tableIpPhones.getItems().addAll(entities);
            tableIpPhones.refresh();
        });
    }

    private void updateDepartments(List<Department> entities) {
        Platform.runLater(() -> {
            // Очистка таблицы
            tableDepartments.getItems().clear();

            // Заполнение таблицы
            tableDepartments.getItems().addAll(entities);
            tableDepartments.refresh();
        });
    }

    private void updateEmployers(List<Employer> entities) {
        Platform.runLater(() -> {
            // Очистка таблицы
            tableEmployers.getItems().clear();

            // Заполнение таблицы
            tableEmployers.getItems().addAll(entities);
            tableEmployers.refresh();
        });
    }

    private void initListeners() {
        butUpdateIpPhones.setOnAction(event -> updateIpPhones(dao.getIpPhones()));
        butUpdateDepartments.setOnAction(event -> updateDepartments(dao.getDepartments()));
        butUpdateEmployers.setOnAction(event -> updateEmployers(dao.getEmployers()));
    }
}
