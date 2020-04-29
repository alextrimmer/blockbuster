package shop.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Record;
import shop.data.Video;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
public class TEST1  {

  private Inventory _inventory = Data.newInventory();



  private void check(Video v, int numOwned, int numOut, int numRentals) {
    Record r = _inventory.get(v);
    Assertions.assertEquals(numOwned, r.numOwned());
    Assertions.assertEquals(numOut, r.numOut());
    Assertions.assertEquals(numRentals, r.numRentals());
  }


  @Test
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();

    Video v1 = Data.newVideo("Avatar", 2009, "Cameron");
    Assertions.assertEquals(0, _inventory.size());
    Assertions.assertTrue(Data.newAddCmd(_inventory, v1, 2).run());
    Assertions.assertEquals(1, _inventory.size());
    Assertions.assertTrue(Data.newAddCmd(_inventory, v1, 2).run());
    Assertions.assertEquals(1, _inventory.size());
    check(v1,4,0,0);



    Video v2 = Data.newVideo("Titanic", 1997, "Cameron");
    Assertions.assertTrue(Data.newAddCmd(_inventory, v2, 1).run());
    Assertions.assertEquals(2, _inventory.size());
    check(v2,1,0,0);

    Assertions.assertFalse(Data.newAddCmd(_inventory, null, 5).run());
    Assertions.assertEquals(2, _inventory.size());

    Assertions.assertTrue(Data.newOutCmd(_inventory, v2).run());
    check(v2,1,1,1);

    Assertions.assertTrue(Data.newInCmd(_inventory, v2).run());
    check(v2,1,0,1);

    Assertions.assertTrue(Data.newAddCmd(_inventory, v2, -1).run());
    Assertions.assertEquals(1, _inventory.size());
    check(v1,4,0,0);

    Assertions.assertTrue(Data.newOutCmd(_inventory, v1).run());
    Assertions.assertTrue(Data.newOutCmd(_inventory, v1).run());
    Assertions.assertTrue(Data.newOutCmd(_inventory, v1).run());
    Assertions.assertTrue(Data.newOutCmd(_inventory, v1).run());
    check(v1,4,4,4);

    Assertions.assertTrue(Data.newInCmd(_inventory, v1).run());
    check(v1,4,3,4);

    Assertions.assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
    Assertions.assertEquals(2, _inventory.size());
    check(v2,5,0,0);
    check(v1,4,3,4);
  }
}

