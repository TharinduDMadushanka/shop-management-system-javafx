package com.dev.pos.controller;

import com.dev.pos.util.Enum.BoType;
import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.CustomerBo;
import com.dev.pos.bo.impl.CustomerBoImpl;
//import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.dto.CustomerDto;
import com.dev.pos.dto.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtSalary;
    public Button btnSave;
    public TextField txtSearch;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn<CustomerTm,Integer> colNo;
    public TableColumn<CustomerTm,String> colName;
    public TableColumn<CustomerTm,String>  colEmail;
    public TableColumn<CustomerTm,String>  colContact;
    public TableColumn<CustomerTm,Double>  colSalary;
    public TableColumn<CustomerTm,Button>  colDelete;

    CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    String searchText="";

    public void initialize() {

        colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button"));

        loadCustomer(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            loadCustomer(searchText);

        });

        // Listen for table

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
            }
        });

    }

    public void SaveOnAction(ActionEvent actionEvent) throws Exception {

        CustomerDto dto = new CustomerDto(
                txtEmail.getText(),
                txtName.getText(),
                txtContact.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        if (btnSave.getText().equalsIgnoreCase("Save Customer")) {
//            CustomerDTO customer = new DatabaseAccessCode().findCustomer(txtEmail.getText());

            CustomerDto customer = customerBo.findCustomer(txtEmail.getText());

            if (customer != null) {
                new Alert(Alert.AlertType.WARNING, "Customer is already saved!").show();
            } else {
//                boolean isSaved = new DatabaseAccessCode().createCustomer(dto);
                boolean isSaved = customerBo.saveCustomer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer has been saved!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Customer could not be saved!").show();
                }
            }
        }else {
//            boolean isUpdated = new DatabaseAccessCode().updateCustomer(dto);
            boolean isUpdated = customerBo.updateCustomer(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Customer has been updated!").show();
                txtEmail.setEditable(true);
                btnSave.setText("Save Customer");
                clearFields();
                loadCustomer(searchText);
            }
        }
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void clearFields() {
        txtEmail.clear();
        txtName.clear();
        txtContact.clear();
        txtSalary.clear();
    }

    private void loadCustomer(String searchText) {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            int counter=1;
            for (CustomerDto dto : customerBo.searchCustomer(searchText)) {
                Button button = new Button("Delete");
                CustomerTm customerTm = new CustomerTm(
                        counter,
                        dto.getEmail(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getSalary(),
                        button
                );
                counter++;
                obList.add(customerTm);

                //Customer Delete
                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure..!", ButtonType.NO,ButtonType.YES);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        try {
//                            boolean isDeleted = new DatabaseAccessCode().deleteCustomer(dto.getEmail());
                            boolean isDeleted = customerBo.deleteCustomer(dto.getEmail());
                            if (isDeleted) {
                                new Alert(Alert.AlertType.INFORMATION, "Customer has been deleted!").show();
                                loadCustomer(searchText);
                                clearFields();
                                btnSave.setText("Save Customer");
                            }else {
                                new Alert(Alert.AlertType.ERROR, "Customer could not be deleted!").show();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }

            tblCustomer.setItems(obList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setData(CustomerTm newValue) {
        txtEmail.setEditable(false);
        btnSave.setText("Update Customer");

        txtName.setText(newValue.getName());
        txtContact.setText(newValue.getContact());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
        txtEmail.setText(newValue.getEmail());
    }

}
