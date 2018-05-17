package com.retinder.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import bsh.This;

/**
 * 
 * @author retinder 提供静态webdriver的实例 提供常见元素查找或搜索操作
 */

public class BasePage {

	WebDriver driver = driverManage.driver;
	static Logger logger = Logger.getLogger(This.class.getName());

	public void mouseOver(WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element, 1, 1).build().perform();
	}

	public void pause(Long timeout) {
		try {
			Thread.currentThread().sleep(timeout);
			logger.info("waitting " + timeout + "ms");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pressKeyborad(int keyCode) {
		try {
			Robot rb = new Robot();
			rb.keyPress(keyCode);
			rb.delay(100);
			rb.keyRelease(keyCode);
			logger.info("press " + keyCode);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void open(String url) {
		logger.info("navigate to " + url);
		driver.get(url);
	}

	public boolean isTextPresent(String text) {
		boolean isPresent = false;
		if (driver.findElement(By.tagName("body")).getText().contains(text)) {
			isPresent = true;
			logger.info("text show up:" + text);
		}
		return isPresent;
	}

	public void clickLink(String linktext) {
		List<WebElement> linkList = driver.findElements(By
				.partialLinkText(linktext));
		if (linkList.size() > 1) {
			logger.info("Error:too many  links have been found!!");
		} else {
			logger.info("click link:" + linktext);
			linkList.get(0).click();
		}

	}

	public void clickButton(String buttonText) {
		List<WebElement> buttonList = driver.findElements(By.tagName("button"));
		if (buttonList.size() > 1) {
			logger.info("Error:too many  button have been found!!");
		} else {
			logger.info("click button:" + buttonText);
			buttonList.get(0).click();
		}
	}

	/**
	 * ele 的日期控件的特殊操作
	 * 
	 * @param type
	 *            type:1:今天 2：确定 3:清空
	 */
	public void pickDate(int type) {
		switch (type) {
		case 1:
			driver.findElement(By.xpath("//*[@id='dpTodayInput']")).click();
			break;

		case 2:
			driver.findElement(By.xpath("//*[@id='dpOKInput']")).click();
			break;
		case 3:
			driver.findElement(By.xpath("//*[@id='dpClearInput']")).click();
			break;

		default:
			break;
		}
	}

	public void select(WebElement we, String option) {
		Select select = new Select(we);
		select.selectByVisibleText(option);
		logger.info(option + " have been select");
	}

	/*
	 * ele select 饿了么的组件展示出来是 <ul> <li/> </ul> 很多前端框架都是这种。特殊的组件，需要重新编写定位的方法
	 */

	public void selectUI(WebElement parent, String option) {
		parent.findElements(By.tagName("li")).stream().map(x -> {
			if (x.getText().equals(option)) {
				x.click();
				logger.info(option + " have been selected!!");
			}
			return null;
		});
	}

	public void enterFrame(String frameXpath) {
		logger.info("enter the frame of xpath：" + frameXpath);
		driver.switchTo().frame(driver.findElement(By.xpath(frameXpath)));
	}

	public void leaveFrame() {
		driver.switchTo().defaultContent();
		logger.info("leave the frame");
	}

	public static class driverManage {
		public static WebDriver driver;
		public static ChromeDriverService server;

		public static void setDriver(String browser) {
			switch (browser) {
			case "chrome":
				server = new ChromeDriverService.Builder()
						.usingDriverExecutable(
								new File("drivers/chromedriver.exe"))
						.usingAnyFreePort().build();
				try {
					server.start();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches",
						Arrays.asList("--start-maximized"));
				driver = new EventFiringWebDriver(new ChromeDriver(server,
						capabilities)).register(new LogEventListener());
				driver.manage().window().maximize();// 启动选项在某些浏览器版本会失效，需要打开窗口后再最大化
				break;
			case "firefox":

				break;

			case "IE":

				break;
			default:
				break;
			}
		}

		public static void stopWebdriver() {
			// 此方法仅在windows下有效，如果是其他系统进行测试，需要自行改写
			Runtime runTime = Runtime.getRuntime();
			try {
				runTime.exec("TASKKILL /F /IM chromedirver.exe");
				runTime.exec("TASKKILL /F /IM chrome.exe");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
