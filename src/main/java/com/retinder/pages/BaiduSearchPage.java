package com.retinder.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.retinder.base.BasePage;

public class BaiduSearchPage extends BasePage {
	
	public BaiduSearchPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driverManage.driver, this);
	}
	
	@FindBy(id="kw")
	WebElement searchInput;
	
	@FindBy(id="su")
	WebElement searchBtn;
	
	@FindBy(id="expectedError")
	WebElement error;
	
	
	/**
	 * 定义这个页面的其中一个操作，输入关键字搜索
	 * @param searchStr
	 */
	public void doSerach(String searchStr){
		open("https://www.baidu.com");
		searchInput.clear();
		searchInput.sendKeys(searchStr);
		pause(2000L);
		searchBtn.click();
	}
	
	/**
	 * 定义这个页面的其中一个操作，输入关键字搜索
	 * @param searchStr
	 */
	public void errorPick(String searchStr){
		open("https://www.baidu.com");
		error.click();
	}

}
