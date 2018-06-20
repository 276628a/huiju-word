package com.huiju.pms.myword.config;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

@Component
public class CommonConfig {

	@NotNull
	@Value("${member.right.app.url.checked}")
	public String[] checked;

	@NotNull
	@Value("${member.right.console.url.nochecked}")
	public String[] nochecked;

	public String[] getChecked() {
		return checked;
	}

	public void setChecked(String[] checked) {
		this.checked = checked;
	}

	public String[] getNochecked() {
		return nochecked;
	}

	public void setNochecked(String[] nochecked) {
		this.nochecked = nochecked;
	}

 
}