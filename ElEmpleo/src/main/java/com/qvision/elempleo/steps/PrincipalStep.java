package com.qvision.elempleo.steps;

import java.io.IOException;

import com.qvision.elempleo.pages.PrincipalPage;

import net.thucydides.core.annotations.Step;

public class PrincipalStep {
	
	PrincipalPage pagePrincipal;
	
	@Step
	public void openurl() {
		pagePrincipal.open();
	}
	
	@Step
	public void search(String search) {
		pagePrincipal.search(search);	
	}
	
	@Step
	public void filters() {
		pagePrincipal.filters();
	}
	
	@Step
	public void readOfferts() throws IOException {
		pagePrincipal.readOfferts();
	}
	
	@Step
	public void screenshot() {
		pagePrincipal.screenshot();
	}
	
	@Step
	public boolean validate() {
		return pagePrincipal.validate();		
	}
	
	
	
	

}
