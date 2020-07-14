package com.example.myapplicationebookssyncfusion;

import java.io.Serializable;

public class Item_Model implements Serializable {

    private String title,subTitle;

    public Item_Model(String title,String subTitle)
    {
    this.title=title;
    this.subTitle=subTitle;
    }

    public String getTitle()
    {
        return  this.title;
    }

    public String getSubTitle()
    {
        return this.subTitle;
    }
}
