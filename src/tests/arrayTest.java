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
}
