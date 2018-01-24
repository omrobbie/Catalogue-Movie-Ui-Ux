/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 10/5/17 3:51 PM.
 */

package com.omrobbie.cataloguemovieuiux;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.omrobbie.cataloguemovieuiux", appContext.getPackageName());
    }
}
