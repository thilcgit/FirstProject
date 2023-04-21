package tester.tests.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count=0;
    int maxRerun=1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxRerun){
            count++;
            return true;
        }
        return false;
    }
}
