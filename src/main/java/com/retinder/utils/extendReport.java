package com.retinder.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import com.relevantcodes.extentreports.ReporterType;

/**
 * 用来生成extentReport报告
 * 
 * @author Administrator
 *
 */
public class extendReport implements IReporter {

	ExtentReports extent;

	@Override
	public void generateReport(List<XmlSuite> xmlsuites, List<ISuite> suites,
			String outputDir) {
		// TODO Auto-generated method stub
		extent = new ExtentReports(outputDir + File.separator
				+ "testReport.html", true, DisplayOrder.OLDEST_FIRST,
				NetworkMode.OFFLINE);
		extent.startReporter(ReporterType.DB, outputDir + File.separator
				+ "testReport.html");
		for (ISuite suite:suites) {
			Map<String, ISuiteResult> result=suite.getResults();
			result.values().forEach(x->{
				setExtentNodes(x.getTestContext().getPassedTests(), LogStatus.PASS);
				setExtentNodes(x.getTestContext().getFailedTests(), LogStatus.FAIL);
				//重跑的，之前的失败会算成skip，所以出报告的时候忽略他
				//setExtentNodes(x.getTestContext().getSkippedTests(), LogStatus.SKIP);
			});
		}
		extent.flush();
		extent.close();
	}

	void setExtentNodes(IResultMap tests, LogStatus status) {
		ExtentTest test = null;
		if (tests.size() > 0) {
			for (ITestResult x : tests.getAllResults()) {
				test = extent.startTest(x.getMethod().getMethodName());
				test.setStartedTime(millisToTime(x.getStartMillis()));
				test.setEndedTime(millisToTime(x.getEndMillis()));
				test.assignCategory(x.getMethod().getGroups());
				if (x.getThrowable() != null) {
					//这里指定截图位置
					//注意如果有开启重试功能，只有最后一次执行会调用onTestFailed
					if(!(x.getAttribute("errorScreenShot")==null))
						test.log(status, test.addScreenCapture("../"+x.getAttribute("errorScreenShot").toString()));
					test.log(status, x.getThrowable());
				} else {
					test.log(status, "Test" + status.toString().toLowerCase()
							+ "ed");
				}

			}
			extent.endTest(test);
		}

	}

	Date millisToTime(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		return c.getTime();
	}

}
