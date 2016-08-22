package openemr_cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature"
		,glue={"stepRunner"},
				format = {"pretty"}
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}
