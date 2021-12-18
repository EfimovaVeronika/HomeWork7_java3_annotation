
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestClass1 {


    @BeforeSuite
    public void printHello() {
        System.out.println("Hello");
    }

    @Test
    public void printImportantMethod() {
        System.out.println("Important method");
    }

    @AfterSuite
    public void printBye() {
        System.out.println("Bye");
    }
}