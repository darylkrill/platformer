package com.stexni.platformer;

import org.lwjgl.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.debug("LWJGL Version: {}", getLWJGLVersion());
    }

    public static String getLWJGLVersion() {
        return Version.getVersion();
    }
}
