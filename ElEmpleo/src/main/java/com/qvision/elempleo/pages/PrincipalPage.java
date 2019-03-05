package com.qvision.elempleo.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBys;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@DefaultUrl("https://www.elempleo.com/co")

public class PrincipalPage extends PageObject {

	@FindBy(xpath = "/html/body/div[4]/section[1]/div[3]/div[1]/div[2]/div/form/div/div/span[1]/input")
	WebElement txtSearch;

	@FindBy(xpath = "/html/body/div[4]/section[1]/div[3]/div[1]/div[2]/div/form/div/div/span[1]/div/div/div[1]")
	WebElement btnSugges;

	@FindBy(xpath = "/html/body/div[4]/section[1]/div[3]/div[1]/div[2]/div/form/div/div/span[2]/input")
	WebElement btnCity;

	@FindBy(xpath = "/html/body/div[4]/section[1]/div[3]/div[1]/div[2]/div/form/div/div/span[2]/div/div/div[2]")
	WebElement txtCity;

	@FindBy(xpath = "/html/body/div[8]/div[2]/h1")
	WebElement lblValidate;

	@FindBy(xpath = "//*[@id=\"politics_cookieCO\"]/div/div[2]/a[2]")
	WebElement btnCookies;

	@FindBy(xpath = "//*[@id=\"salary1\"]")
	WebElementFacade btnSalario;

	@FindBys({ @FindBy(id = "WorkAreaFilter1"), @FindBy(xpath = "//*[@id=\"WorkAreaFilter1\"]/option[2]") })
	WebElement listAreadeTrabajo;

	@FindBy(xpath = "//*[@id=\"WorkAreaFilter1\"]/option[2]")
	WebElement txtAreadeTrabajo;

	@FindBy(xpath = "/html/body/div[8]/div[3]/div[1]/h2/span[1]/strong[2]")
	WebElement txtNumeroDeResultados;
	
	@FindBy (xpath ="/html/body/div[4]/section[1]/div[3]/div[1]/div[2]/div/form/div/div/span[1]/input")
	WebElement btnSearch;
	
	

	WebElement txtTituloDeOferta;
	WebElement txtEmpresa;

	FileWriter fichero = null;
	PrintWriter pw = null;
	boolean initValid = false;
	boolean isSearch = false;
	boolean isSearch2 = false;

	public void search(String search) {		
		btnCookies.click();
		while(initValid=false)
		initValid = btnCookies.isEnabled();
		btnSearch.click();
		txtSearch.sendKeys(search);
		btnSugges.click();
		btnCity.click();
		txtCity.click();
	}

	public boolean validate() {		
		String valid = lblValidate.getText();
		try {
			if (valid.equals("Empleos Contador en Bogotá") ) {
				isSearch = true;
			}
			if(isSearch = true) {
				isSearch = btnSalario.isSelected();
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		return isSearch;
	}

	public void filters() {
		waitFor(listAreadeTrabajo).isClickable();
		listAreadeTrabajo.click();
		for(int i=0; i<60;i++)
			waitFor(btnSalario).isClickable();
		btnSalario.click();		
	}

	public void readOfferts() throws IOException {
		try {
			fichero = new FileWriter("Files\\Jobs.txt");
			pw = new PrintWriter(fichero);
			int numEntero = Integer.parseInt(txtNumeroDeResultados.getText());
			while (isSearch2=false)
				isSearch2 = btnSalario.isSelected();
			for (int i = 1; i <= numEntero; i++) {
				txtTituloDeOferta = find(
						By.xpath("/html/body/div[8]/div[4]/div[1]/div[3]/div[" + i + "]/div[1]/ul/li[1]/h2/a"));
				String txtOferta = txtTituloDeOferta.getText();
				txtEmpresa = find(By.xpath(
						"/html/body/div[8]/div[4]/div[1]/div[3]/div[" + i + "]/div[1]/ul/li[2]/h3/span[2]/span"));
				String txtEmpresas = txtEmpresa.getText();
				pw.println("Titulo de la Oferta : "+txtOferta + "\n" +"Empresa : "+ txtEmpresas + "\n" + "**********************************");
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void screenshot () {
		try {
			TakesScreenshot scrShot =((TakesScreenshot)getDriver());
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File("Evidencia\\"+System.currentTimeMillis()+".png");
			FileUtils.copyFile(SrcFile, DestFile);
				}
				catch (Exception e) {
					System.out.println("ERROR EN SS!"+e);
				}
		}
	
}
