package org.mysolution;

import java.util.Scanner;

public class UnitsInteraction extends ConsoleInteraction<Double> {
    public UnitsInteraction(Scanner scanner, Boolean quit) {
        super(scanner, quit);
        value = 0.0d;
    }

    @Override
    public Double prompt() {
        do {
            if (quit) break;
            System.out.println("Enter record's number of units [.01-99999.0]: ");
            String inputString = scanner.nextLine();
            //inputString = console.readLine("Enter record's number of units [.01-99999.0]: ");
            quit = "q".equalsIgnoreCase(inputString);
            if (quit) break;
            try {
                value = Double.parseDouble(inputString);
            } catch (NumberFormatException ex) {
                System.out.println("Error: units can only be [.01-99999.0]");
            }
        } while (invalid());
        return value;
    }

    @Override
    protected boolean invalid() {
        if (value > 0 && value < 100000) {
            return false;
        } else {
            System.out.println("Error: units can only be [.01-99999.0]");
            return true;
        }
    }
}
