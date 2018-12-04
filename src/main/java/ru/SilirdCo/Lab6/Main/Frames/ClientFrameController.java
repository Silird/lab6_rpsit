package ru.SilirdCo.Lab6.Main.Frames;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.Lab6.Main.DAO.DAOClient;
import ru.SilirdCo.Lab6.Main.DAO.IDAO;
import ru.SilirdCo.Lab6.Main.Entities.Department;
import ru.SilirdCo.Lab6.Main.Entities.Employer;
import ru.SilirdCo.Lab6.Main.Entities.IpPhone;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientFrameController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(ClientFrameController.class);

    private IDAO dao = new DAOClient();

    @FXML
    private TextField textIpPhoneId;
    private IntegerTextField intIpPhoneId;
    @FXML
    private TextField textIpPhoneName;
    @FXML
    private TextField textIpPhoneNumber;
    @FXML
    private Button butIpPhoneSend;
    @FXML
    private Button butIpPhoneClear;

    @FXML
    private TextField textDepartmentId;
    private IntegerTextField intDepartmentId;
    @FXML
    private TextField textDepartmentName;
    @FXML
    private TextField textDepartmentNumber;
    @FXML
    private Button butDepartmentSend;
    @FXML
    private Button butDepartmentClear;

    @FXML
    private TextField textEmployerId;
    private IntegerTextField intEmployerId;
    @FXML
    private TextField textEmployerName;
    @FXML
    private TextField textEmployerDepartment;
    @FXML
    private TextField textEmployerIpPhone;
    @FXML
    private Button butEmployerSend;
    @FXML
    private Button butEmployerClear;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intIpPhoneId = new IntegerTextField(textIpPhoneId);
        intDepartmentId = new IntegerTextField(textDepartmentId);
        intEmployerId = new IntegerTextField(textEmployerId);
        butDepartmentClear.setDisable(true);
        butEmployerClear.setDisable(true);
        butIpPhoneClear.setDisable(true);

        initListener();
    }

    private void initListener() {
        butIpPhoneSend.setOnAction(event -> {
            butIpPhoneSend.setDisable(true);
            Platform.runLater(() -> {
                if (intIpPhoneId.getInteger() != 0) {
                    int id = intIpPhoneId.getInteger();
                    IpPhone ipPhone = dao.getIpPhoneById(id);
                    setIpPhone(ipPhone);
                }
                else if (!textIpPhoneName.getText().equals("")) {
                    String name = textIpPhoneName.getText();
                    IpPhone ipPhone = dao.getIpPhoneByName(name);
                    setIpPhone(ipPhone);
                }
                else if (!textIpPhoneNumber.getText().equals("")) {
                    String number = textIpPhoneNumber.getText();
                    IpPhone ipPhone = dao.getIpPhoneByNumber(number);
                    setIpPhone(ipPhone);
                }
                else {
                    sendWarn("Данные не введены");
                    butIpPhoneSend.setDisable(false);
                    return;
                }

                butIpPhoneClear.setDisable(false);
            });
        });

        butIpPhoneClear.setOnAction(event -> {
            butIpPhoneClear.setDisable(true);

            intIpPhoneId.setInteger(0);
            textIpPhoneId.setText("");
            textIpPhoneName.setText("");
            textIpPhoneNumber.setText("");

            butIpPhoneSend.setDisable(false);
        });


        butDepartmentSend.setOnAction(event -> {
            butDepartmentSend.setDisable(true);
            Platform.runLater(() -> {
                if (intDepartmentId.getInteger() != 0) {
                    int id = intDepartmentId.getInteger();
                    Department department = dao.getDepartmentById(id);
                    setDepartment(department);
                }
                else if (!textDepartmentName.getText().equals("")) {
                    String name = textDepartmentName.getText();
                    Department department = dao.getDepartmentByName(name);
                    setDepartment(department);
                }
                else if (!textDepartmentNumber.getText().equals("")) {
                    String number = textDepartmentNumber.getText();
                    Department department = dao.getDepartmentByNumber(number);
                    setDepartment(department);
                }
                else {
                    sendWarn("Данные не введены");
                    butDepartmentSend.setDisable(false);
                    return;
                }

                butDepartmentClear.setDisable(false);
            });
        });

        butDepartmentClear.setOnAction(event -> {
            butDepartmentClear.setDisable(true);

            intDepartmentId.setInteger(0);
            textDepartmentId.setText("");
            textDepartmentName.setText("");
            textDepartmentNumber.setText("");

            butDepartmentSend.setDisable(false);
        });


        butEmployerSend.setOnAction(event -> {
            butEmployerSend.setDisable(true);
            Platform.runLater(() -> {
                if (intEmployerId.getInteger() != 0) {
                    int id = intEmployerId.getInteger();
                    Employer employer = dao.getEmployerById(id);
                    setEmployer(employer);
                }
                else if (!textEmployerName.getText().equals("")) {
                    String name = textEmployerName.getText();
                    Employer employer = dao.getEmployerByName(name);
                    setEmployer(employer);
                }
                else if (!textEmployerDepartment.getText().equals("")) {
                    String department = textEmployerDepartment.getText();
                    Employer employer = dao.getEmployerByDepartment(department);
                    setEmployer(employer);
                }
                else if (!textEmployerIpPhone.getText().equals("")) {
                    String ipPhone = textEmployerIpPhone.getText();
                    Employer employer = dao.getEmployerByIpPhone(ipPhone);
                    setEmployer(employer);
                }
                else {
                    sendWarn("Данные не введены");
                    butEmployerSend.setDisable(false);
                    return;
                }

                butEmployerClear.setDisable(false);
            });
        });

        butEmployerClear.setOnAction(event -> {
            butEmployerClear.setDisable(true);

            intEmployerId.setInteger(0);
            textEmployerId.setText("");
            textEmployerName.setText("");
            textEmployerDepartment.setText("");
            textEmployerIpPhone.setText("");

            butEmployerSend.setDisable(false);
        });
    }

    private void setIpPhone(IpPhone ipPhone) {
        intIpPhoneId.setInteger(ipPhone.getId());
        textIpPhoneName.setText(ipPhone.getName());
        textIpPhoneNumber.setText(ipPhone.getNumber());
    }

    private void setDepartment(Department department) {
        intDepartmentId.setInteger(department.getId());
        textDepartmentName.setText(department.getName());
        textDepartmentNumber.setText(department.getNumber());
    }

    private void setEmployer(Employer employer) {
        intEmployerId.setInteger(employer.getId());
        textEmployerName.setText(employer.getName());
        textEmployerDepartment.setText(employer.getDepartment().getName());
        textEmployerIpPhone.setText(employer.getIpPhone().getName());
    }

    private void sendWarn(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING, text);
        alert.showAndWait();
    }
}
