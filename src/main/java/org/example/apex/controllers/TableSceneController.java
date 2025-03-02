package org.example.apex.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.apex.utils.ContentLoader;
import org.example.apex.utils.list_objects.Account;

import java.net.URL;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.ResourceBundle;
/*
*            new Account("Google.com", "log1", "abc123"),
                new Account("Google.com", "log2", "ZaqWsx123123"),
                new Account("Google.com", "log3", "NJSC&679A"),
                new Account("Google.com", "log4", "KJNSDJ:KNSCD:KS A#$asa"),
                new Account("Google.com", "log1", "abc123"),
                new Account("Google.com", "log2", "ZaqWsx123123"),
                new Account("Google.com", "log3", "NJSC&679A"),
                new Account("Google.com", "log4", "KJNSDJ:KNSCD:KS A#$asa"),
                new Account("Google.com", "log1", "abc123"),
                new Account("Google.com", "log2", "ZaqWsx123123"),
                new Account("Google.com", "log3", "NJSC&679A"),
                new Account("Google.com", "log4", "KJNSDJ:KNSCD:KS A#$asa"),
                new Account("Google.com", "log1", "abc123"),
                new Account("Google.com", "log2", "ZaqWsx123123"),
                new Account("Google.com", "log3", "NJSC&679A"),
                new Account("Google.com", "log4", "KJNSDJ:KNSCD:KS A#$asa"),
                new Account("Google.com", "log1", "abc123"),
                new Account("Google.com", "log2", "ZaqWsx123123"),
                new Account("Google.com", "log3", "NJSC&679A"),
                new Account("Google.com", "log4", "KJNSDJ:KNSCD:KS A#$asa"),
                new Account("Google.com", "log1", "abc123"),
                new Account("Google.com", "log2", "ZaqWsx123123"),
                new Account("Google.com", "log3", "NJSC&679A"),
                new Account("Google.com", "log4", "KJNSDJ:KNSCD:KS A#$asa"),
                new Account("Google.com", "log1", "abc123"),
                new Account("Google.com", "log2", "ZaqWsx123123"),
                new Account("Google.com", "log3", "NJSC&679A"),
                new Account("Google.com", "log4", "KJNSDJ:KNSCD:KS A#$asa"),
                new Account("Google.com", "log1", "abc123"),
                new Account("Google.com", "log2", "ZaqWsx123123"),
                new Account("Google.com", "log3", "NJSC&679A"),
                new Account("Google.com", "log4", "KJNSDJ:KNSCD:KS A#$asa"),
                new Account("Google.com", "log1", "abc123"),
                new Account("Google.com", "log2", "ZaqWsx123123"),
                new Account("Google.com", "log3", "NJSC&679A"),
                new Account("Google.com", "log4", "KJNSDJ:KNSCD:KS A#$asa")*/

// Controller for the table scene
public class TableSceneController implements Initializable {
    // Table and columns
    @FXML private TableView<Account> table;
    @FXML private TableColumn<Account, String> login_cmn, password_cmn, platform_cmn, rel_cmn;

    private ContentLoader contentLoader;
    private ArrayList<Account> accountsList;

    // Add object (not implemented)
    @FXML void addObject(MouseEvent event) {
        System.out.println("Add Object");
    }

    // Editing selected account
    @FXML void editObject(MouseEvent event) {
        System.out.println("Edit Object");
    }

    // Delete object (not implemented)
    @FXML void deleteObject(MouseEvent event) {
        System.out.println("Delete Object");
    }

    // Search object (not implemented)
    @FXML void find(MouseEvent event) {
        System.out.println("Find object");
    }

    // Initialize the table with sample data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (contentLoader == null)
            contentLoader = new ContentLoader("/Users/daniilkrasnov/IdeaProjects/Apex/src/main/java/org/example/apex/test/ApexFolder");
        if (accountsList == null)
            accountsList = contentLoader.loadAccounts();

        ObservableList<Account> people = FXCollections.observableArrayList(accountsList);
        // Bind columns to Account properties
        platform_cmn.setCellValueFactory(new PropertyValueFactory<>("platform"));
        login_cmn.setCellValueFactory(new PropertyValueFactory<>("login"));
        password_cmn.setCellValueFactory(new PropertyValueFactory<>("password"));
        rel_cmn.setCellValueFactory(new PropertyValueFactory<>("reliability"));

        // Set data to the table
        table.setItems(people);
    }
}