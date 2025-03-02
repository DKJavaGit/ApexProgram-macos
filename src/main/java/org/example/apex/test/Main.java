package org.example.apex.test;

import org.example.apex.security.PassGenerator;
import org.example.apex.utils.ContentLoader;
import org.example.apex.utils.list_objects.Account;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            accounts.add(new Account("Google.com", ("SimpleAcc"+(i+1)), PassGenerator.genFourthLevelPass(20)));
        }

        ContentLoader loader = new ContentLoader("/Users/daniilkrasnov/IdeaProjects/Apex/src/main/java/org/example/apex/test/ApexFolder", accounts);
        loader.saveAccounts();

    }
}
