package org.example.apex.utils.list_objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.example.apex.security.PassGenerator;

import java.io.*;

/**
 * Represents an account with platform, login, password, and reliability information.
 * Implements custom serialization to handle JavaFX StringProperty fields.
 */
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // Ensures version compatibility during serialization

    // Transient fields to exclude them from default serialization
    private transient StringProperty platform;
    private transient StringProperty login;
    private transient StringProperty password;
    private transient StringProperty reliability;

    /**
     * Constructs an Account object with the specified platform, login, and password.
     * Calculates the reliability of the password automatically.
     *
     * @param platform The platform associated with the account.
     * @param login    The login username for the account.
     * @param password The password for the account.
     */
    public Account(String platform, String login, String password) {
        this.platform = new SimpleStringProperty(platform);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.reliability = new SimpleStringProperty(PassGenerator.calculateReliability(password) * 25 + " %");
    }

    // Getters and Setters for platform
    public String getPlatform() {
        return platform.get();
    }

    public void setPlatform(String platform) {
        this.platform.set(platform);
    }

    public StringProperty platformProperty() {
        return platform;
    }

    // Getters and Setters for login
    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public StringProperty loginProperty() {
        return login;
    }

    // Getters and Setters for password
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
        // Update reliability when password is changed
        this.reliability.set(PassGenerator.calculateReliability(password) * 25 + " %");
    }

    public StringProperty passwordProperty() {
        return password;
    }

    // Getters and Setters for reliability
    public String getReliability() {
        return reliability.get();
    }

    public void setReliability(String reliability) {
        this.reliability.set(reliability);
    }

    public StringProperty reliabilityProperty() {
        return reliability;
    }

    /**
     * Custom serialization method to handle transient StringProperty fields.
     *
     * @param out The ObjectOutputStream to write the object to.
     * @throws IOException If an I/O error occurs during serialization.
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Perform default serialization
        out.writeObject(getPlatform()); // Serialize platform as a String
        out.writeObject(getLogin());    // Serialize login as a String
        out.writeObject(getPassword()); // Serialize password as a String
        out.writeObject(getReliability()); // Serialize reliability as a String
    }

    /**
     * Custom deserialization method to handle transient StringProperty fields.
     *
     * @param in The ObjectInputStream to read the object from.
     * @throws IOException            If an I/O error occurs during deserialization.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Perform default deserialization
        // Reinitialize transient fields with deserialized values
        platform = new SimpleStringProperty((String) in.readObject());
        login = new SimpleStringProperty((String) in.readObject());
        password = new SimpleStringProperty((String) in.readObject());
        reliability = new SimpleStringProperty((String) in.readObject());
    }
}