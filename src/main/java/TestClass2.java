import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestClass2 {

    @BeforeSuite
    public void printHelloWorld() {
        System.out.println("Hello World");
    }

    @Test(priority = 6)
    public void printVeryImportantMethod() {
        System.out.println("Very important Method");
    }

    @Test(priority = 4)
    public void printVeryImportantMethodToo() {
        System.out.println("Very important Method Too");
    }

    @AfterSuite
    public void printBayBey() {
        System.out.println("Bye-Bye");
    }
}

