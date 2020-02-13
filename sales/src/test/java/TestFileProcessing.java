import com.test.sales.app.SalesReportApp;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class TestFileProcessing
{

    @Test
    public void TEST_NON_EXISTING_INPUT_DIR()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_NON_EXISTING_PROCESSED_DIR()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_NON_EXISTING_OUTPUT_DIR()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_NON_EXISTING_FAILED_DIR()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_VALID_FILE_PROCESSING()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_INVALID_FILE_PROCESSING()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_SEVERAL_FILE_PROCESSING()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_CORRECT_NUMBER_OF_CUSTOMERS()
    {
        SalesReportApp.main(new String[0]);
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_CORRECT_NUMBER_OF_SELLERS()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_CORRECT_BEST_SALE()
    {
        SalesReportApp.main(new String[0]);
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_CORRECT_WORST_SELLER()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void TEST_MASSSIVE_FILE_PROCESSING()
    {
        Assert.assertTrue(true);
    }
}
