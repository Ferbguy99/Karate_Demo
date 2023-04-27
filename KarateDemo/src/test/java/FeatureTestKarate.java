
import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;
import static org.junit.Assert.assertEquals;

import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

@CucumberOptions(features = "src/test/resources/Features", tags = {"@redirectDisable"})
public class FeatureTestKarate {
    @BeforeClass
    public static void beforeClass() {
        try {
            FileUtils.deleteDirectory(new File("./target/surefire-reports"));
            FileUtils.deleteDirectory(new File("./report/cucumber-html-reports"));
        } catch (Exception e) {
            System.err.println("Unable to delete directory. Error -" + e.getMessage());
        }
    }

    @Test
    public void testParallel() {
        String karateOutputPath = "target/surefire-reports";
        KarateStats stats = CucumberRunner.parallel(getClass(), 1, karateOutputPath);

        generateReport(karateOutputPath);
        boolean skipFailures = Boolean.valueOf(System.getProperty("skipFailures", "false"));
        int failCount = stats.getFailCount();
        System.out.println("Skip Failures JVM property value -" + skipFailures);
        if (skipFailures && failCount != 0) {
            System.out.println("Skipping build failure - " + failCount);
        } else {
            assertEquals("There are scenario failures", 0, failCount);
        }
    }

    private static void generateReport(String karateOutputPath) {
            Properties prop = null;
            try {
                prop = new Properties();
                prop.load(new FileInputStream("./src/test/resources/utility.properties"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Collection<File> jsonFiles = FileUtils
                    .listFiles(new File(karateOutputPath), new String[]{"json"}, true);

            List<String> jsonPaths = new ArrayList(jsonFiles.size());
            for (File file : jsonFiles) {
                jsonPaths.add(file.getAbsolutePath());
            }

            File file = new File("report");
            file.mkdirs();
            Configuration config = new Configuration(file, prop.getProperty("reportName"));
            ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
            reportBuilder.generateReports();
    }
}

