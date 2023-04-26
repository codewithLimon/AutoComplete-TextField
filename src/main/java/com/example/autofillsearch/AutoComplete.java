package com.example.autofillsearch;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class AutoComplete{

    @FXML
    private TextField textField;

    private ListView<String> listView;

    private List<String> autocompleteValues = List.of("Apple", "Banana", "Cherry", "Durian", "Elderberry");

    public void initialize() {
        listView = new ListView<>();
        listView.setPrefWidth(textField.getWidth());
        listView.setVisible(false);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            List<String> filteredValues = autocompleteValues.stream()
                    .filter(value -> value.toLowerCase().startsWith(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            if (!filteredValues.isEmpty()) {
                listView.setItems(FXCollections.observableArrayList(filteredValues));
                listView.setVisible(true);
            } else {
                listView.getItems().clear();
                listView.setVisible(false);
            }
        });
        listView.setOnMouseClicked(event -> {
            String selectedValue = listView.getSelectionModel().getSelectedItem();
            textField.setText(selectedValue);
            listView.setVisible(false);
        });

        VBox root = new VBox(textField, listView);
        root.setSpacing(5.0);
        root.setAlignment(Pos.TOP_LEFT);
        Scene scene = new Scene(root, 300, 250);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
