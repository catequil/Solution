package org.mysolution;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String inputString = null;
        int numRecords = 0;
        boolean quit = false;

        Double marketPrice = 0d;
        LocalDate marketDate = LocalDate.now();
        EntityRepo repo = new EntityRepo();
        Scanner scanner = new Scanner(System.in);
        if (scanner != null) {
            while (!quit) {
                boolean invalid = true;
                System.out.println("Press 'q' at anytime to quit.");
                System.out.println("Enter number of records to process [0-100]: ");
                inputString = scanner.nextLine();
                quit = "q".equalsIgnoreCase(inputString);
                if (!quit) {
                    numRecords = Integer.parseInt(inputString);
                    if (numRecords > 100) {
                        numRecords = 100;
                    }
                    for (int i = 0; i < numRecords; i++) {
                        String type = "";
                        String employeeId = "";
                        LocalDate vestingDate = LocalDate.now();
                        Double units = 0d;
                        Double grantPrice = 0d;
                        invalid = true;
                        while (invalid && !quit) {
                            System.out.println("Enter record's type [VEST,PERF,SALE]: ");
                            inputString = scanner.nextLine();
                            //inputString = console.readLine("Enter record's type [VEST,PERF,SALE]: ");
                            quit = "q".equalsIgnoreCase(inputString);
                            if (Option.TYPES.contains(inputString.toUpperCase())) {
                                type = inputString;
                                invalid = false;
                            } else {
                                System.out.println("Error: must be [VEST,PERF,SALE]");
                            }
                        }

                        invalid = true;
                        while (invalid && !quit) {
                            System.out.println("Enter record's employee id [a-zA-Z0-9]: ");
                            inputString = scanner.nextLine();
                            //inputString = console.readLine("Enter record's employee id [a-zA-Z0-9]: ");
                            quit = "q".equalsIgnoreCase(inputString);
                            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
                            if (inputString.matches(pattern.pattern())) {
                                employeeId = inputString.toUpperCase();
                                invalid = false;
                            } else {
                                System.out.println("Error: employee id can only be [a-zA-Z0-9]");
                            }
                        }

                        invalid = true;
                        while (invalid && !quit) {
                            System.out.println("Enter record's vesting date [YYYYMMDD]: ");
                            inputString = scanner.nextLine();
                            //inputString = console.readLine("Enter record's vesting date [YYYYMMDD]: ");
                            quit = "q".equalsIgnoreCase(inputString);
                            try {
                                vestingDate = LocalDate.parse(inputString, DateTimeFormatter.ofPattern("yyyyMMdd"));
                                invalid = false;
                            } catch (DateTimeParseException ex) {
                                System.out.println("Error: vesting date can only be [YYYYMMDD]");
                            }
                        }

                        invalid = true;
                        while (invalid && !quit) {
                            System.out.println("Enter record's number of units [.01-99999.0]: ");
                            inputString = scanner.nextLine();
                            //inputString = console.readLine("Enter record's number of units [.01-99999.0]: ");
                            quit = "q".equalsIgnoreCase(inputString);
                            try {
                                units = Double.parseDouble(inputString);
                                if (units > 0 && units < 100000) {
                                    invalid = false;
                                } else {
                                    System.out.println("Error: units can only be [.01-99999.0]");
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("Error: units can only be [.01-99999.0]");
                            }
                        }

                        invalid = true;
                        while (invalid && !quit) {
                            System.out.println("Enter record's grant price [.01-99999.0]: ");
                            inputString = scanner.nextLine();
                            //inputString = console.readLine("Enter record's grant price [.01-99999.0]: ");
                            quit = "q".equalsIgnoreCase(inputString);
                            try {
                                grantPrice = Double.parseDouble(inputString);
                                if (grantPrice > 0 && grantPrice < 100000) {
                                    invalid = false;
                                } else {
                                    System.out.println("Error: grant price can only be [.01-99999.0]");
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("Error: grant price can only be [.01-99999.0]");
                            }
                        }

                        Optional<Employee> optionalEmployee = repo.find(Employee.class, employeeId);
                        Employee employee;
                        if (optionalEmployee.isPresent()) {
                            employee = optionalEmployee.get();
                        } else {
                            employee = repo.create(Employee.class);
                        }
                        employee.setId(employeeId);

                        Optional<Option> optionalOption = repo.find(Option.class, "" + i);
                        Option option;
                        if (optionalOption.isPresent()) {
                            option = optionalOption.get();
                        } else {
                            option = repo.create(Option.class);
                        }
                        option.setId("" + i);
                        option.setEmployee(employee);
                        option.setVestingDate(vestingDate);
                        option.setUnits(units);
                        option.setGrantPrice(grantPrice);
                        employee.addOption(option);
                        repo.save(employee);
                    }

                    invalid = true;
                    while (invalid && !quit) {
                        System.out.println("Enter market date [YYYYMMDD]: ");
                        inputString = scanner.nextLine();
                        //inputString = console.readLine("Enter market date [YYYYMMDD]: ");
                        quit = "q".equalsIgnoreCase(inputString);
                        try {
                            marketDate = LocalDate.parse(inputString, DateTimeFormatter.ofPattern("yyyyMMdd"));
                            invalid = false;
                        } catch (DateTimeParseException ex) {
                            System.out.println("Error: market date can only be [YYYYMMDD]");
                        }
                    }

                    invalid = true;
                    while (invalid && !quit) {
                        System.out.println("Enter market price [.01-99999.0]: ");
                        inputString = scanner.nextLine();
                        //inputString = console.readLine("Enter market price [.01-99999.0]: ");
                        quit = "q".equalsIgnoreCase(inputString);
                        try {
                            marketPrice = Double.parseDouble(inputString);
                            if (marketPrice > 0 && marketPrice < 100000) {
                                invalid = false;
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("Error: market price can only be [.01-99999.0]");
                        }
                    }
                }

                for (Employee employee : repo.getList(Employee.class)) {
                    Double cashGain = 0d;
                    for (Option option : employee.getOptions()) {
                        cashGain += option.getCashGain(marketPrice, marketDate);
                    }
                    System.out.println(employee.getId() + "," + cashGain);
                }
                quit = true;
            }

        }
    }
}
