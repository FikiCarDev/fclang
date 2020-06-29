import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class if_test {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void classicIf() {
        main m = new main();
        main.runParser("tests/if/if_test_1.fclang");
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nFive\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void elseIf() {
        main m = new main();
        main.runParser("tests/if/if_test_2.fclang");
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nThree\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void advanceIf() {
        main m = new main();
        main.runParser("tests/if/if_test_3.fclang");
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nYes\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void boolExpresionIf(){
        main m = new main();
        main.runParser("tests/if/if_test_4.fclang");
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nYes\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}