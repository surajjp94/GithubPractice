package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="com.ecom.automation.test.stepdefinitions",
monochrome = true, plugin= {"html:target/cucmber.html"})
public class TestNgTestRunner extends AbstractTestNGCucumberTests{

}
