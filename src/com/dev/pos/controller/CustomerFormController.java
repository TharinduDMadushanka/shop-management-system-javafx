package com.dev.pos.controller;

import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.CustomerBo;
import com.dev.pos.dto.CustomerDto;
import com.dev.pos.dto.tm.CustomerTm;
import com.dev.pos.util.Enum.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class CustomerFormController {

    public Button btnSave;
    @FXML
    private TableColumn<CustomerTm,String> colContact;

    @FXML
    private TableColumn<CustomerTm, Button> colDelete;

    @FXML
    private TableColumn<CustomerTm,String> colEmail;

    @FXML
    private TableColumn<CustomerTm,String> colName;

    @FXML
    private TableColumn<CustomerTm,Integer> colNo;

    @FXML
    private TableColumn<CustomerTm,Double> colSalary;

    @FXML
    private AnchorPane customerContext;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

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

    @FXML
    void SaveOnAction(ActionEvent event) throws Exception {

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
