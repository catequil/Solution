package org.mysolution;

import java.util.Scanner;

public class TypeInteraction extends ConsoleInteraction<String> {

    public TypeInteraction(Scanner scanner, Boolean quit) {
        super(scanner, quit);
    }

    @Override
    public String prompt() {
        do {
            if (getQuit()) break;
            System.out.println("Enter record's type [VEST,PERF,SALE]: ");
            String inputString = getScanner().nextLine();
            setQuit("q".equalsIgnoreCase(inputString));
            if (getQuit()) break;
            setValue(inputString);

        } while (invalid());
        return getValue();
    }

    @Override
    protected boolean invalid() {
        if (getValue() != null && Option.TYPES.contains(getValue().toUpperCase())) {
            return false;
        } else {
            System.out.println("Error: must be [VEST,PERF,SALE]");
            return  true;
        }
    }
}
