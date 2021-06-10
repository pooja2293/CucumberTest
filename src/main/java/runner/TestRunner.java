package runner;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:\\Users\\Pooja\\eclipse-workspace\\PageObjectBDDFramework\\src\\main\\java\\Feature",
		tags = "@first, @second",
		glue= {"StepDefinition"},
		plugin= {"pretty", "html:test-output"},
		monochrome = false
		
		)






public class TestRunner {
private TestNGCucumberRunner testNGCucumberRunner;


@BeforeClass(alwaysRun=true)
public void setup(){
	testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	
}


@Test(groups="cucumber", description="run cucumber", dataProvider = "features")
public void features(CucumberFeatureWrapper cucumberFeature) {
	testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
}
	
@DataProvider
public Object[][] features(){
	return testNGCucumberRunner.provideFeatures();
}


@AfterClass(alwaysRun=true)
public void tearDown() {
	testNGCucumberRunner.finish();
}
}


