
package meli.mutantdetector.api.utils;

import java.io.File;

public class Constants {

    public static final String VERSION = "1.0.0";

    public static final File CONFIG_FILE = new File("config/mutantdetectorapi.cfg");
    public static final String LOG4J_FILE = "config/log4j.properties";
    
    public static final String INIT_MESSAGE = "\n########################################\n###  The Mutant Detector Application ###\n###           Version  " + Constants.VERSION + "         ###\n########################################";
    
    public static final String V_ARG = "-v";
    public static final String VERSION_ARG = "--version";
    
    public static final String APPLICATION_EXIT = "Application exit.";
    
    public static final String PORT = "port";
    public static final String THREADS = "threads";
    
}