package org.mysolution;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.Optional;

public class EntityRepoTest extends TestCase {


    EntityRepo repo;

    @Override
    protected void setUp() {
        repo = new EntityRepo();
    }


    @Test
    public void testCreate() {
        Option option = repo.create(Option.class);

        Assert.assertNull(option.getId());
        Assert.assertNull(option.getUnits());
        Assert.assertNull(option.getGrantPrice());

    }

    @Test
    public void testSave() {
        Option option = repo.create(Option.class);
        option.setId("1");
        repo.save(option);
    }

    @Test
    public void testFind() {
        Option option = repo.create(Option.class);
        option.setId("1");
        repo.save(option);

        Optional<Option> optionalOption = repo.find(Option.class, "1");
        if (optionalOption.isPresent()) {
            Option option1 = optionalOption.get();
            Assert.assertEquals("1", option1.getId());
        } else {
            Assert.fail("Option not found");
        }
    }

    @Test
    public void testGetList() {
        Option option = repo.create(Option.class);
        option.setId("1");
        repo.save(option);

        List<Option> options = repo.getList(Option.class);

        Assert.assertEquals(1, options.size());
        Assert.assertEquals(options.get(0).getId(), "1");

    }
}
