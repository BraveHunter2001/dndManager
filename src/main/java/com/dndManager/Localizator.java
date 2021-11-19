package com.dndManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class Localizator {

    public enum Languages {en, ru}

    private ResourceBundle currentResource;
    private  Languages currentLang;

    private final Languages defaultLang = Languages.en;
    private static Localizator instance;

    public Localizator()
    {
        this.setLang(defaultLang);
    }

    public static Localizator getInstance()
    {
        if(instance == null) {
            instance = new Localizator();
        }
        return instance;
    }

    public Localizator(HttpServletRequest request) {
        this.setLang(request, true);
    }
    public void init(Languages lang) {
        this.setLang(lang);
    }

    public void setLang(HttpServletRequest request, boolean setDefaultIfNull) {
        Languages lang;
        try {
            String langStr = request.getParameter("lang");
            lang = Languages.valueOf(langStr);
            if(lang == currentLang)
                return;
        }
        catch (Exception e)
        {
            if(setDefaultIfNull)
                lang = defaultLang;
            else
                return;
        }
        currentResource = ResourceBundle.getBundle("tables", new Locale(lang.name()));
        currentLang = lang;
    }

    public void setLang(Languages lang)
    {
        try {
            if(lang == currentLang)
                return;
        }
        catch (Exception e)
        {
            lang = defaultLang;
        }
        currentResource = ResourceBundle.getBundle("tables", new Locale(lang.name()));
        currentLang = lang;
    }

    public String getResource(String key)
    {
        if(key==null || key.trim().isEmpty())
            return "";
        return currentResource.getString(key);
    }
}
