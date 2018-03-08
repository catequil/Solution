package org.mysolution;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class Application {

    Scanner scanner;
    EntityRepo repo;
    String inputString;
    Boolean quit = false;

    public Application(Scanner scanner, EntityRepo entityRepo) {
        this.scanner = scanner;
        this.repo = entityRepo;
    }

    public void run() {
        if (scanner != null) {
            while (!quit) {
                System.out.println("Press 'q' at anytime to quit.");
                System.out.println("Enter number of records to process [0-100]: ");
                inputString = scanner.nextLine();
                quit = "q".equalsIgnoreCase(inputString);
                if (quit) break;

                int numRecords = Integer.parseInt(inputString);
                if (numRecords > 100) {
                    numRecords = 100;
                }
                for (int i = 0; i < numRecords; i++) {
                    String type = new TypeInteraction(scanner, quit).prompt();
                    if (quit) break;
                    String employeeId = new EmployeeIdInteraction(scanner, quit).prompt();
                    if (quit) break;
                    LocalDate vestingDate = new VestingDateInteraction(scanner, quit).prompt();
                    if (quit) break;
                    Double units = new UnitsInteraction(scanner, quit).prompt();
                    if (quit) break;
                    Double grantPrice = new GrantPriceInteraction(scanner, quit).prompt();
                    if (quit) break;

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

                LocalDate marketDate = new MarketDateInteraction(scanner, quit).prompt();
                if (quit) break;
                Double marketPrice = new MarketPriceInteraction(scanner, quit).prompt();
                if (quit) break;

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
