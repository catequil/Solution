package org.mysolution;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

public class Option extends Entity {
    public static final Collection<String> TYPES = Arrays.asList(new String[] { "VEST","PERF","SALE" });

    private Employee employee;
    private Double units;
    private LocalDate vestingDate;
    private Double grantPrice;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public LocalDate getVestingDate() {
        return vestingDate;
    }

    public void setVestingDate(LocalDate vestingDate) {
        this.vestingDate = vestingDate;
    }

    public Double getGrantPrice() {
        return grantPrice;
    }

    public void setGrantPrice(Double grantPrice) {
        this.grantPrice = grantPrice;
    }

    public Double getCashGain(Double marketPrice, LocalDate marketDate) {

        if (!isVested(marketDate) || isUnderWater(marketPrice)) {
            return 0.0d;
        }
        return units * (marketPrice - grantPrice);
    }

    private boolean isUnderWater(Double marketPrice) {
        return marketPrice < grantPrice;
    }

    private boolean isVested(LocalDate marketDate) {
        return marketDate.isAfter(vestingDate);
    }
}
