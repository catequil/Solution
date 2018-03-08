package org.mysolution;

import java.util.Scanner;

public abstract class ConsoleInteraction<T> {

    Scanner scanner;
    Boolean quit;
    T value;

    public ConsoleInteraction(Scanner scanner, Boolean quit) {
        this.scanner = scanner;
        this.quit = quit;
    }

    public abstract T prompt();

    protected abstract boolean invalid();
}
