package org.mysolution;

import java.util.Scanner;
import java.util.regex.Pattern;

public class EmployeeIdInteraction extends ConsoleInteraction<String> {

    public EmployeeIdInteraction(Scanner scanner, Boolean quit) {
        super(scanner, quit);
    }

    @Override
    public String prompt() {
        do {
            if (quit) break;
            System.out.println("Enter record's employee id [a-zA-Z0-9]: ");
            String inputString = scanner.nextLine();
            //inputString = console.readLine("Enter record's employee id [a-zA-Z0-9]: ");
            quit = "q".equalsIgnoreCase(inputString);
            if (quit) break;
            value = inputString.toUpperCase();
        } while (invalid());
        return value;
    }

    @Override
    protected boolean invalid() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if (value.matches(pattern.pattern())) {
            return false;
        } else {
            System.out.println("Error: employee id can only be [a-zA-Z0-9]");
            return true;
        }
    }
}
