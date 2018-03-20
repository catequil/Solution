package org.mysolution;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class VestingDateInteraction extends  ConsoleInteraction<LocalDate> {

    public VestingDateInteraction(Scanner scanner, Boolean quit) {
        super(scanner, quit);
    }

    @Override
    public LocalDate prompt() {
        do {
            if (getQuit()) break;
            System.out.println("Enter record's vesting date [YYYYMMDD]: ");
            String inputString = getScanner().nextLine();
            setQuit("q".equalsIgnoreCase(inputString));
            if (getQuit()) break;
            try {
                setValue(LocalDate.parse(inputString, DateTimeFormatter.ofPattern("yyyyMMdd")));
            } catch (DateTimeParseException ex) {
                System.out.println("Error: vesting date can only be [YYYYMMDD]");
            }
        } while (invalid());
        return getValue();
    }

    @Override
    protected boolean invalid() {
        return getValue() == null;
    }
}
