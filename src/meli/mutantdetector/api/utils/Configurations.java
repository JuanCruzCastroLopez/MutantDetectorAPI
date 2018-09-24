/* AMCO @ 2018 */
package meli.mutantdetector.api.utils;

import ace.Ace;
import ace.gson.Json;
import ace.text.Strings;
import com.google.gson.JsonObject;
import java.io.File;
import meli.mutantdetector.api.MutantDetectorAPI;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Configurations {

    private static Logger _logger = Logger.getLogger(MutantDetectorAPI.class);
    
    private int _port;
    private int _threads;
    
    public Configurations() {
        _port = 0;
        _threads = 0;
    }

    public boolean setUpLog() {
        if (!new File(Constants.LOG4J_FILE).exists()) {
            System.err.println(Strings.concat("No se encontró el archivo de configuración: '", Constants.LOG4J_FILE, "'."));
            System.err.println(Constants.APPLICATION_EXIT);
            return false;
        }
        PropertyConfigurator.configureAndWatch(Constants.LOG4J_FILE);
        return true;
    }

    private boolean validateConfig(final JsonObject config) {
        //TODO: Realizar el json de configuración
        if (Ace.assigned(config)) {
            if (config.has("port") && config.has("threads")) {
                return true;
            }
        }
        return false;
    }

    public boolean loadApiConfig() {
        if (!Constants.CONFIG_FILE.exists()) {
            _logger.error(Strings.concat("No se encontró el archivo de configuración: ", Constants.CONFIG_FILE.getAbsolutePath(), "."));
            return false;
        }
        final JsonObject config = Json.readFileAsJsonObject(Constants.CONFIG_FILE);
        if (!validateConfig(config)) {
            _logger.error("La configuración no es correcta, por favor verificar.");
            return false;
        }
        _port = Json.obtainInteger(config, Constants.PORT);
        _threads = Json.obtainInteger(config, Constants.THREADS);
        return true;
    }
    
    public int getPort() {
        return _port;
    }
    
    public int getThreads() {
        return _threads;
    }

}
