import com.test.sales.processor.FileProcessor;
import com.test.sales.watcher.AppConstants;
import org.junit.Assert;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class TestFileProcessing
{

    @Test
    public void TEST_NON_EXISTING_INPUT_DIR()
    {
        try
        {
            Files.delete(Paths.get("src/test/resources/in"));
            Files.copy(Paths.get("src/test/resources/inputFiles/testSalesFile _INVALID.txt"), Paths.get("src/test/resources/in/testSalesFile _INVALID.txt"), StandardCopyOption.REPLACE_EXISTING);
            FileProcessor processor = new FileProcessor(Paths.get("testSalesFile _INVALID.txt"));
            processor.run();
        }
        catch(Exception e)
        {
            Assert.assertTrue(Files.notExists(Paths.get("src/test/resources/in")));

            try
            {
                Files.createDirectory(Paths.get("src/test/resources/in"));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }
    }

    @Test
    public void TEST_VALID_FILE_PROCESSING()
    {
        try
        {
            if(Files.exists(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")))
                Files.delete(Paths.get("src/test/resources/out/testSalesFile.txt_output.txt"));
            Files.copy(Paths.get("src/test/resources/inputFiles/testSalesFile.txt"), Paths.get("src/test/resources/in/testSalesFile.txt"), StandardCopyOption.REPLACE_EXISTING);
            FileProcessor processor = new FileProcessor(Paths.get("testSalesFile.txt"));
            processor.run();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        Assert.assertTrue(Files.exists(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")));
    }

    @Test
    public void TEST_INVALID_FILE_PROCESSING()
    {
        try
        {
            if(Files.exists(Paths.get(AppConstants.FAILED_DIR + "testSalesFile _INVALID.txt")))
                Files.delete(Paths.get("src/test/resources/fld/testSalesFile _INVALID.txt"));

            Files.copy(Paths.get("src/test/resources/inputFiles/testSalesFile _INVALID.txt"), Paths.get("src/test/resources/in/testSalesFile _INVALID.txt"), StandardCopyOption.REPLACE_EXISTING);
            FileProcessor processor = new FileProcessor(Paths.get("testSalesFile _INVALID.txt"));
            processor.run();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        Assert.assertTrue(Files.exists(Paths.get(AppConstants.FAILED_DIR + "testSalesFile _INVALID.txt")));

    }

    @Test
    public void TEST_CORRECT_NUMBER_OF_CUSTOMERS()
    {
        try
        {
            if(Files.exists(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")))
                Files.delete(Paths.get("src/test/resources/out/testSalesFile.txt_output.txt"));
            Files.copy(Paths.get("src/test/resources/inputFiles/testSalesFile.txt"), Paths.get("src/test/resources/in/testSalesFile.txt"), StandardCopyOption.REPLACE_EXISTING);
            FileProcessor processor = new FileProcessor(Paths.get("testSalesFile.txt"));
            processor.run();

            List<String> lines = Files.lines(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")).collect(Collectors.toList());

            Assert.assertTrue(Integer.parseInt((lines.get(0).split(":")[1]).trim()) == 2);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void TEST_CORRECT_NUMBER_OF_SELLERS()
    {
        try
        {
            if(Files.exists(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")))
                Files.delete(Paths.get("src/test/resources/out/testSalesFile.txt_output.txt"));
            Files.copy(Paths.get("src/test/resources/inputFiles/testSalesFile.txt"), Paths.get("src/test/resources/in/testSalesFile.txt"), StandardCopyOption.REPLACE_EXISTING);
            FileProcessor processor = new FileProcessor(Paths.get("testSalesFile.txt"));
            processor.run();

            List<String> lines = Files.lines(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")).collect(Collectors.toList());

            Assert.assertTrue(Integer.parseInt((lines.get(1).split(":")[1]).trim()) == 2);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void TEST_CORRECT_BEST_SALE()
    {
        try
        {
            if(Files.exists(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")))
                Files.delete(Paths.get("src/test/resources/out/testSalesFile.txt_output.txt"));
            Files.copy(Paths.get("src/test/resources/inputFiles/testSalesFile.txt"), Paths.get("src/test/resources/in/testSalesFile.txt"), StandardCopyOption.REPLACE_EXISTING);
            FileProcessor processor = new FileProcessor(Paths.get("testSalesFile.txt"));
            processor.run();

            List<String> lines = Files.lines(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")).collect(Collectors.toList());

            Assert.assertTrue(Integer.parseInt((lines.get(2).split(":")[1]).trim()) == 51);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void TEST_CORRECT_WORST_SELLER()
    {
        try
        {
            if(Files.exists(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")))
                Files.delete(Paths.get("src/test/resources/out/testSalesFile.txt_output.txt"));
            Files.copy(Paths.get("src/test/resources/inputFiles/testSalesFile.txt"), Paths.get("src/test/resources/in/testSalesFile.txt"), StandardCopyOption.REPLACE_EXISTING);
            FileProcessor processor = new FileProcessor(Paths.get("testSalesFile.txt"));
            processor.run();

            List<String> lines = Files.lines(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile.txt_output.txt")).collect(Collectors.toList());

            Assert.assertTrue((lines.get(3).split(":")[1]).trim().equals("Paulo"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void TEST_MASSSIVE_FILE_PROCESSING()
    {
        try
        {
            if(Files.exists(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile2.txt_output.txt")))
                Files.delete(Paths.get("src/test/resources/out/testSalesFile2.txt_output.txt"));
            Files.copy(Paths.get("src/test/resources/inputFiles/testSalesFile2.txt"), Paths.get("src/test/resources/in/testSalesFile2.txt"), StandardCopyOption.REPLACE_EXISTING);
            FileProcessor processor = new FileProcessor(Paths.get("testSalesFile2.txt"));
            processor.run();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        Assert.assertTrue(Files.exists(Paths.get(AppConstants.OUTPUT_FILE_DIR + "testSalesFile2.txt_output.txt")));
    }
}
