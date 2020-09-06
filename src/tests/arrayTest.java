import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class arrayTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void arrayDeclaration() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void arraySize() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest2.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n10\n10\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void arraySort() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest3.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void setIntArray() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest4.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void setDecimalArray() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest5.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void setStringArray() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest6.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void setBoolArray() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest7.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getIntArray() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest8.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n4\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getDecimalArray() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest9.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n4.0\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getStringArray() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest11.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nTest\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getBoolArray() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest10.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\ntrue\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getUndefinedArrayPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/array/arrayTest12.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n3\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}
