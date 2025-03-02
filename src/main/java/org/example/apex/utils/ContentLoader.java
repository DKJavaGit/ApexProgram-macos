package org.example.apex.utils;

import org.example.apex.utils.list_objects.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for saving and loading Account objects to/from a file.
 * Handles serialization and deserialization of Account objects.
 */
public class ContentLoader {
    private ArrayList<Account> accounts; // List of accounts to be saved or loaded
    private final String APEX_FOLDER;   // Directory path where account data is stored

    /**
     * Constructor to initialize the ContentLoader with the APEX folder path.
     *
     * @param APEX_FOLDER The path to the directory where account data is stored.
     */
    public ContentLoader(String APEX_FOLDER) {
        this(APEX_FOLDER, null); // Delegates to the main constructor
    }

    /**
     * Main constructor to initialize the ContentLoader with the APEX folder path and an initial list of accounts.
     *
     * @param APEX_FOLDER The path to the directory where account data is stored.
     * @param accounts    The initial list of accounts (can be null).
     */
    public ContentLoader(String APEX_FOLDER, ArrayList<Account> accounts) {
        this.APEX_FOLDER = APEX_FOLDER;
        this.accounts = accounts;
    }

    /**
     * Saves the list of accounts to a file in the specified APEX folder.
     * Creates the directory and file if they do not exist.
     *
     * @throws RuntimeException If the directory or file cannot be created, or if serialization fails.
     */
    public void saveAccounts() {
        File accountsFolder = new File(APEX_FOLDER + "/Apex_Accounts");
        File accountsFile = new File(accountsFolder, "accounts.ser");

        // Ensure the directory exists
        if (!accountsFolder.exists() && !accountsFolder.mkdirs()) {
            throw new RuntimeException("Failed to create directory: " + accountsFolder.getAbsolutePath());
        }

        // Ensure the file exists
        if (!accountsFile.exists()) {
            try {
                if (!accountsFile.createNewFile()) {
                    throw new RuntimeException("Failed to create file: " + accountsFile.getAbsolutePath());
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to create file: " + accountsFile.getAbsolutePath(), e);
            }
        }

        // Serialize the list of accounts to the file
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(accountsFile))) {
            output.writeObject(accounts);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save accounts to file: " + accountsFile.getAbsolutePath(), e);
        }
    }

    /**
     * Loads the list of accounts from the file in the specified APEX folder.
     *
     * @return A list of Account objects, or null if the file does not exist or an error occurs.
     */
    public ArrayList<Account> loadAccounts() {
        File accountsFile = new File(APEX_FOLDER + "/Apex_Accounts/accounts.ser");

        // Check if the file exists before attempting to load
        if (!accountsFile.exists()) {
            System.out.println("Accounts file does not exist: " + accountsFile.getAbsolutePath());
            return null;
        }

        // Deserialize the list of accounts from the file
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(accountsFile))) {
            return (ArrayList<Account>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load accounts: " + e.getMessage());
            return null;
        }
    }

    /**
     * Returns the current list of accounts.
     *
     * @return The list of accounts.
     */
    public ArrayList<Account> getAccountsList() {
        return accounts;
    }

    /**
     * Sets the list of accounts.
     *
     * @param accounts The list of accounts to set.
     */
    public void setAccountsList(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Returns the APEX folder path.
     *
     * @return The APEX folder path.
     */
    public String getAPEX_FOLDER() {
        return APEX_FOLDER;
    }
}