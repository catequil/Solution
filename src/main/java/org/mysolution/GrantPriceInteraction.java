package org.mysolution;

import java.util.Scanner;

public class GrantPriceInteraction extends ConsoleInteraction<Double> {

    public GrantPriceInteraction(Scanner scanner, Boolean quit) {
        super(scanner, quit);
        setValue(0.0d);
    }

    @Override
    public Double prompt() {
        do {
            if (getQuit()) break;
            System.out.println("Enter record's grant price [.01-99999.0]: ");
            String inputString = getScanner().nextLine();
            setQuit("q".equalsIgnoreCase(inputString));
            if (getQuit()) break;
            try {
                setValue(Double.parseDouble(inputString));
            } catch (NumberFormatException ex) {
                System.out.println("Error: grant price can only be [.01-99999.0]");
            }
        } while (invalid());
        return getValue();
    }

    @Override
    protected boolean invalid() {
        if (getValue() > 0 && getValue() < 100000) {
            return false;
        } else {
            System.out.println("Error: grant price can only be [.01-99999.0]");
            return true;
        }
    }
}
