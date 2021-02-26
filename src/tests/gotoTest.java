import com.fikicar.Main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class gotoTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void classicGoto() {
        String[] t = {"./scripts/tests/goto/gotoTest1.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nYes\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void ifGoto() {
        String[] t = {"./scripts/tests/goto/gotoTest2.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nSize doesn't matter\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void forGoto() {
        String[] t = {"./scripts/tests/goto/gotoTest3.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n50\nStopped for-loop on 50\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}