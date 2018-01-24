/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 11/8/17 3:16 PM.
 */

package com.omrobbie.cataloguemovieuiux.util;

import java.util.Locale;

/**
 * Created by omrobbie on 11/10/2017.
 */

public class Language {

    public static String getCountry() {
        String country = Locale.getDefault().getCountry().toLowerCase();

        switch (country) {
            case "id":
                break;

            default:
                country = "en";
                break;
        }

        return country;
    }
}
