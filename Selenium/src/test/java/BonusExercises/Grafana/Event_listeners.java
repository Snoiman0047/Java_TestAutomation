package BonusExercises.Grafana;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Event_listeners implements ITestListener
{
    public void onStart(ITestContext execution) {
        System.out.println("---------------------- Starting Execution ------------------");
        try {
            FileUtils.deleteDirectory(new File("./test-recordings/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext execution) {
        System.out.println("---------------------- Ending Execution ------------------");
    }

    public void onTestStart(ITestResult test) {
        System.out.println("---------------------- Test: " + test.getName() + " Started ------------------");
        try {
            MonteScreenRecorder.startRecord(test.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSuccess(ITestResult test) {
        System.out.println("---------------------- Test: " + test.getName() + " Passed ------------------");
        try {
            MonteScreenRecorder.stopRecord();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File( "C:/Users/snoim/OneDrive/Desktop/Automation/QA Automation/TestAutomation/Selenium/src/test/test-recordings/" + test.getName() + ".avi");
        if(file.delete()) {
            System.out.println("Recorded Screen Cast File Deleted Successfully");
        }
        else {
            System.out.println("Failed to Delete the Recorded Screen Cast File");
        }
    }

    public void onTestFailure(ITestResult test) {
        System.out.println("---------------------- Test "  + test.getName() + " Failed ------------------");
        try {
            MonteScreenRecorder.stopRecord();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
