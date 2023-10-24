import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    float x = 3.3F;
    Student st = new Student();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void print_test() {
        x = st.print();
        assertEquals(x,1);
    }


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void GPA_test() {
        st.GPA();
        assertEquals("GPA", outputStreamCaptor.toString().trim()); // 使用trim()确保没有额外的空白或换行
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    public void view_test() {
        float x,y;
        x = 3.3F;
        y = st.view();
        assertEquals(y,x,0.5);
    }
}
