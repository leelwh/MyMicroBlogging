package com.example.apuser.mymicroblogging.domain.repository.api.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "breakfast_menu")
public class BreakFastMenu
{
    @ElementList(inline = true)
    List<Food> foodList;
}
