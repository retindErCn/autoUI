package com.retinder.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.retinder.base.BasePage.driverManage;
import static org.assertj.core.api.Assertions.*;

public class BaseTest {
	
	@BeforeClass
	public void setUP(){
		driverManage.setDriver("chrome");
	}
	
	
	@AfterTest
	public void tearDown(){
		driverManage.stopWebdriver();
	}

}
