package com.stexni.platformer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {
    @Test
    public void getLWJGLVersion() throws Exception {
        Assert.assertNotNull(Application.getLWJGLVersion());
    }

}