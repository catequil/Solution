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
            if (getQuit()) break;
            System.out.println("Enter record's employee id [a-zA-Z0-9]: ");
            String inputString = getScanner().nextLine();
            setQuit("q".equalsIgnoreCase(inputString));
            if (getQuit()) break;
            setValue(inputString.toUpperCase());
        } while (invalid());
        return getValue();
    }

    @Override
    protected boolean invalid() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if (getValue().matches(pattern.pattern())) {
            return false;
        } else {
            System.out.println("Error: employee id can only be [a-zA-Z0-9]");
            return true;
        }
    }
}
