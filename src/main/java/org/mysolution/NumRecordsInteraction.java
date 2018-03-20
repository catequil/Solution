package org.mysolution;

import java.util.Scanner;

public class NumRecordsInteraction extends ConsoleInteraction<Integer> {

    public NumRecordsInteraction(Scanner scanner, Boolean quit) {
        super(scanner, quit);
    }

    @Override
    public Integer prompt() {

        System.out.println("Press 'q' at anytime to quit.");

        do {
            if (getQuit()) break;
            System.out.println("Enter number of records to process [0-100]: ");
            String inputString = getScanner().nextLine();
            setQuit("q".equalsIgnoreCase(inputString));
            if (getQuit()) break;
            try {
                setValue(Integer.parseInt(inputString));
            } catch (NumberFormatException ex) {
                System.out.println("Error: number of records can only be [1-100]");
            }
        } while (invalid());
        return getValue();
    }

    @Override
    protected boolean invalid() {
        if (getValue() != null && getValue() > 0 && getValue() < 100) {
            return false;
        } else {
            System.out.println("Error: number of records can only be [1-100]");
            return true;
        }
    }
}
