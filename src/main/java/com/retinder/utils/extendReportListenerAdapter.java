package com.retinder.utils;

import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.ExtentTest;
import com.retinder.base.screenShot;

import bsh.This;

/**
 * 用来给extendReports做集成
 * @author Administrator
 *
 */
public class extendReportListenerAdapter extends TestListenerAdapter {
	
	Logger logger=Logger.getLogger(This.class.getName());
	extendReport extent;
	ExtentTest test;

	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSuccess(tr);
	}
	
	/**
	 * 出错的时候自动截屏
	 */

	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailure(tr);
		screenShot t=new screenShot();
		logger.info("take a screenShot when things go wrong!!");
		tr.setAttribute("errorScreenShot", t.tackScreenShot(null, null));
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSkipped(tr);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailedButWithinSuccessPercentage(tr);
	}

	@Override
	public void onStart(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onStart(testContext);
		
		
	}

	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onFinish(testContext);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		super.onTestStart(result);
	}
	


}
