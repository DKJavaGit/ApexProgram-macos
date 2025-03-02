package org.example.apex.security;

import org.example.apex.exceptions.CipherNotFound;

public class CoderMachine {
    private Coder coderMachine;
    private String cipher;
    private String text;

    public CoderMachine(String text) {
        this("NomadCore", text);
    }

    public CoderMachine(String cipher, String text) {
        this.cipher = cipher;
        this.text = text;
        ininitilizeCore();
    }

    private void ininitilizeCore(byte[] key, byte[] iv) {
        coderMachine = switch (cipher.toUpperCase()) {
            case "NOMADCORE" -> new NomadCore(text, key, iv);
            case "DES" -> new DESCore(text, key, iv);
            case "PBE" -> new PBECore(text, key, iv);
            default -> throw new CipherNotFound("Данного шифра у нас пока нет (");
        };
    }

    private void ininitilizeCore() {
        coderMachine = switch (cipher.toUpperCase()) {
            case "NOMADCORE" -> new NomadCore(text);
            case "DES" -> new DESCore(text);
            case "PBE" -> new PBECore(text);
            default -> throw new CipherNotFound("Данного шифра у нас пока нет (");
        };
    }


    private String operation(String operation) {
        if (operation.equals("enc"))
            return coderMachine.encrypt();
        return coderMachine.decrypt();
    }

    public String encrypt()
    {
        return operation("enc");
    }

    public String encrypt(byte[] key, byte[] iv) {
        ininitilizeCore(key, iv);
        return operation("enc");
    }

    public String decrypt() {
        return operation("dec");
    }

    public String decrypt(byte[] key, byte[] iv) {
        ininitilizeCore(key, iv);
        return operation("dec");
    }

    /*
     * Сеттеры и геттеры
     */
    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCipher() {
        return cipher;
    }

    public String getText() {
        return text;
    }
}
