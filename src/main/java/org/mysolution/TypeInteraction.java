package org.mysolution;

import java.util.Scanner;

public class TypeInteraction extends ConsoleInteraction<String> {

    public TypeInteraction(Scanner scanner, Boolean quit) {
        super(scanner, quit);
    }

    @Override
    public String prompt() {
        do {
            if (quit) break;
            System.out.println("Enter record's type [VEST,PERF,SALE]: ");
            String inputString = scanner.nextLine();
            //inputString = console.readLine("Enter record's type [VEST,PERF,SALE]: ");
            quit = "q".equalsIgnoreCase(inputString);
            if (quit) break;
            value = inputString;

        } while (invalid());
        return value;
    }

    @Override
    protected boolean invalid() {
        if (value != null && Option.TYPES.contains(value.toUpperCase())) {
            return false;
        } else {
            System.out.println("Error: must be [VEST,PERF,SALE]");
            return  true;
        }
    }
}
