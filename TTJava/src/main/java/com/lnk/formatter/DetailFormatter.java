/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.formatter;

import com.lnk.pojo.Stores;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Jackie's PC
 */
public class DetailFormatter implements Formatter<Stores> {
       @Override
    public String print(Stores role, Locale locale) {
        return String.valueOf(role.getStoreId());
    }

    @Override
    public Stores parse(String roleId, Locale locale) throws ParseException {
        return new Stores(Integer.parseInt(roleId));
    }
}
