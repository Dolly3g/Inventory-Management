package com.inventoryManagement;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainStatementTest {
    private List<Store> stores;
    private Store brazil;
    private Store argentina;
    private Product brazilIPod;
    private Product argentinaIPod;

    @Before
    public void setUp() throws Exception {
        stores = new ArrayList<>();
        brazil = new Store("Brazil");
        argentina = new Store("Argentina");
        brazilIPod = new Product("iPod", new Price(100));
        argentinaIPod = new Product("iPod", new Price(50));
        brazil.addStock(brazilIPod, new Quantity(100));
        argentina.addStock(argentinaIPod, new Quantity(100));
        stores.add(brazil);
        stores.add(argentina);
    }

    @Test
    public void testMainStatementCanBeRepresentedByString () {
        MainStatement statement = new MainStatement(stores, null);
        String actual = statement.toString();
        String expected = "Brazil\niPod : 100\nArgentina\niPod : 100\n";
        assertEquals(expected, actual);
    }
}