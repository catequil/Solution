package org.mysolution;

import java.util.ArrayList;
import java.util.Collection;

public class Employee extends Entity {
    private Collection<Option> options = new ArrayList<>();

    public Collection<Option> getOptions() {
        return options;
    }

    public void setOptions(Collection<Option> options) {
        this.options = options;
    }

    public void addOption(Option option) {
        options.add(option);
    }
}
