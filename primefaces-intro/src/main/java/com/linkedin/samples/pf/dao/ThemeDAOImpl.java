package com.linkedin.samples.pf.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedHashSet;
import javax.annotation.PostConstruct;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 *
 * @author SIGINT-X
 */
@Named("themeDao")
public class ThemeDAOImpl {

    private LinkedHashSet<String> themeList;

    @PostConstruct
    public void init() {
        themePrep();
    }

    public void themeSwitch(ValueChangeEvent currentTheme) {
        System.out.println("Changing theme to "+currentTheme.getNewValue());
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("primefaces.THEME",currentTheme.getNewValue());
    }
    public void themeSwitch() {
        System.out.println("Changing theme to");
        FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap().put("primefaces.THEME","");
    }
    public void themeSwitch(AjaxBehaviorEvent currentTheme) {
        System.out.println("Changing theme to "+((UIInput)currentTheme.getComponent()).getValue());
        FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap().put("primefaces.THEME",((UIInput)currentTheme.getComponent()).getValue());
    }

    public void themePrep() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        themeList = new LinkedHashSet<>();
        InputStream input = classLoader.getResourceAsStream("theme-list.txt");
        InputStreamReader reader = new InputStreamReader(input, Charset.forName("utf-8"));
        BufferedReader bReader = new BufferedReader(reader);
        bReader.lines().forEach(line -> {
            themeList.add(line.trim());
        });
    }

    public LinkedHashSet<String> getThemeList() {
        return themeList;
    }

}
