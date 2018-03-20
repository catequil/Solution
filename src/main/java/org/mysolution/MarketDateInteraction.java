package org.mysolution;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MarketDateInteraction extends ConsoleInteraction<LocalDate> {
    public MarketDateInteraction(Scanner scanner, Boolean quit) {
        super(scanner, quit);
    }

    @Override
    public LocalDate prompt() {
        do {
            if (getQuit()) break;
            System.out.println("Enter market date [YYYYMMDD]: ");
            String inputString = getScanner().nextLine();
            setQuit("q".equalsIgnoreCase(inputString));
            if (getQuit()) break;
            try {
                setValue(LocalDate.parse(inputString, DateTimeFormatter.ofPattern("yyyyMMdd")));
            } catch (DateTimeParseException ex) {
                System.out.println("Error: market date can only be [YYYYMMDD]");
            }
        } while (invalid());
        return getValue();
    }

    @Override
    protected boolean invalid() {
        return getValue() == null;
    }
}
