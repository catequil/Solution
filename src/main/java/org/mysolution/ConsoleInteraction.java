package org.mysolution;

import java.util.Scanner;

public abstract class ConsoleInteraction<T> {

    private Scanner scanner;
    private Boolean quit;
    private T value;

    public ConsoleInteraction(Scanner scanner, Boolean quit) {
        this.scanner = scanner;
        this.quit = quit;
    }

    public abstract T prompt();

    protected abstract boolean invalid();

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Boolean getQuit() {
        return quit;
    }

    public void setQuit(Boolean quit) {
        this.quit = quit;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
