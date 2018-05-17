package com.retinder.test;



import org.testng.annotations.Test;

import com.retinder.base.BaseTest;
import com.retinder.pages.BaiduSearchPage;

import static org.assertj.core.api.Assertions.*;

public class BaiduSearchTest extends BaseTest {

	@Test
	public void searchTest() throws InterruptedException {
		BaiduSearchPage searchPage = new BaiduSearchPage();
		searchPage.doSerach("selenium");
		searchPage.pause(2000L);
		assertThat(searchPage.isTextPresent("selenium")).as("search result check!!")
				.isEqualTo(true);
	}
	
	@Test
	public void errorTest(){
		BaiduSearchPage searchPage = new BaiduSearchPage();
		searchPage.errorPick("selenium");
		searchPage.pause(2000L);
		assertThat(searchPage.isTextPresent("selenium")).as("search result check!!")
				.isEqualTo(true);
	}
}
