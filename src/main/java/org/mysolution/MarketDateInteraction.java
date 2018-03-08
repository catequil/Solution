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
            if (quit) break;
            System.out.println("Enter market date [YYYYMMDD]: ");
            String inputString = scanner.nextLine();
            //inputString = console.readLine("Enter market date [YYYYMMDD]: ");
            quit = "q".equalsIgnoreCase(inputString);
            if (quit) break;
            try {
                value = LocalDate.parse(inputString, DateTimeFormatter.ofPattern("yyyyMMdd"));
            } catch (DateTimeParseException ex) {
                System.out.println("Error: market date can only be [YYYYMMDD]");
            }
        } while (invalid());
        return value;
    }

    @Override
    protected boolean invalid() {
        return value == null;
    }
}
