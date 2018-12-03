package ru.SilirdCo.Lab6.Main.Frames;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"unused", "WeakerAccess"})
public class IntegerTextField {
    private final static Logger logger = LoggerFactory.getLogger(IntegerTextField.class);

    private TextField textField;

    private ObjectProperty<Integer> integerProp = null;

    public final ObjectProperty<Integer> integerProperty() {
        if (integerProp == null) {
            integerProp = new SimpleObjectProperty<>();
            integerProp.setValue(0);

        }
        return integerProp;
    }

    public IntegerTextField(TextField textField) {
        this.textField = textField;
        initListeners();
    }

    private void initListeners() {
        if (textField != null) {

            textField.setAlignment(Pos.CENTER_RIGHT);

            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.equals("")) {
                    try {
                        Integer result = Integer.parseInt(newValue);

                        integerProperty().setValue(result);
                    }
                    catch (NumberFormatException ex) {
                        textField.setText(oldValue);
                    }
                }
            });
        }
        else {
            logger.warn("Текстовое поле не назначено");
        }
    }

    public Integer getInteger() {
        return integerProperty().get();
    }

    public void setInteger(Integer value) {
        if (value == null) {
            textField.setText("");
        }
        else {
            textField.setText(String.valueOf(value));
        }
    }
}
