package org.mysolution;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class Application {

    Scanner scanner;
    EntityRepo repo;
    Boolean quit = false;

    public Application(Scanner scanner, EntityRepo entityRepo) {
        this.scanner = scanner;
        this.repo = entityRepo;
    }

    public void run() {
        if (scanner != null) {
            while (!quit) {
                Integer numRecords = new NumRecordsInteraction(scanner, quit).prompt();
                gatherRecords(numRecords);
                print();
            }

        }
    }

    private void gatherRecords(Integer numRecords) {
        quit = numRecords == null;
        if (!quit) {
            for (int i = 0; i < numRecords; i++) {
                String type = new TypeInteraction(scanner, quit).prompt();
                String employeeId = new EmployeeIdInteraction(scanner, quit).prompt();
                LocalDate vestingDate = new VestingDateInteraction(scanner, quit).prompt();
                Double units = new UnitsInteraction(scanner, quit).prompt();
                Double grantPrice = new GrantPriceInteraction(scanner, quit).prompt();

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
        }
    }

    private void print() {
        if (!quit) {
            LocalDate marketDate = new MarketDateInteraction(scanner, quit).prompt();
            Double marketPrice = new MarketPriceInteraction(scanner, quit).prompt();

            for (Employee employee : repo.getList(Employee.class)) {
                Double cashGain = 0d;
                for (Option option : employee.getOptions()) {
                    cashGain += option.getCashGain(marketPrice, marketDate);
                }
                DecimalFormat money = new DecimalFormat("$#0.00");
                System.out.println(employee.getId() + "," + money.format(cashGain));
            }
        }
    }
}
