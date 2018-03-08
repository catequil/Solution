package org.mysolution;

import java.util.Scanner;

public class MarketPriceInteraction extends ConsoleInteraction<Double> {
    public MarketPriceInteraction(Scanner scanner, Boolean quit) {
        super(scanner, quit);
    }

    @Override
    public Double prompt() {
        do {
            if (quit) break;
            System.out.println("Enter market price [.01-99999.0]: ");
            String inputString = scanner.nextLine();
            //inputString = console.readLine("Enter market price [.01-99999.0]: ");
            quit = "q".equalsIgnoreCase(inputString);
            if (quit) break;
            try {
                value = Double.parseDouble(inputString);
            } catch (NumberFormatException ex) {
                System.out.println("Error: market price can only be [.01-99999.0]");
            }
        } while (invalid());
        return value;
    }

    @Override
    protected boolean invalid() {
        if (value > 0 && value < 100000) {
            return false;
        }
        return true;
    }
}
