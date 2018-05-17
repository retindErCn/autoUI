package com.retinder.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import bsh.This;

public class testRetryListener implements IRetryAnalyzer,
		IAnnotationTransformer {
	static Logger logger = Logger.getLogger(This.class.getName());
	final int maxCount = 2;
	int runTimes = 1;

	@Override
	public boolean retry(ITestResult testResult) {

		if (runTimes < maxCount) {
			runTimes++;
			logger.info("第" + runTimes + "次重跑");
			return true;
		}
		return false;
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();

		if (retry == null) {
			annotation.setRetryAnalyzer(testRetryListener.class);
		}

	}

}
