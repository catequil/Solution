package org.mysolution;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EntityRepo repo = new EntityRepo();
        Scanner scanner = new Scanner(System.in);
        Application app = new Application(scanner, repo);
        app.run();
    }
}
