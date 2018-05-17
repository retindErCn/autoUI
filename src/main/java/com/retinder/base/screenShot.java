package com.retinder.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.NewSessionPayload;

import com.retinder.base.BasePage.driverManage;

public class screenShot {

	public String tackScreenShot(WebDriver driver, String element) {
		if (driver==null) {
			driver=driverManage.driver;
		}
		String dir = "screenShot";
		String time = new SimpleDateFormat("yyyyMMdd-HHmmss")
				.format(new Date());
		String shotPath = dir + File.separator + time + ".png";
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(9999, 9999));
		try {
			File sourceFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(shotPath));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return shotPath;
	}

}
