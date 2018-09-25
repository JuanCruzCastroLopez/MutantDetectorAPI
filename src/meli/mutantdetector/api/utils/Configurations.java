package meli.mutantdetector.api.utils;

import ace.Ace;
import ace.gson.Json;
import ace.text.Strings;
import com.google.gson.JsonObject;
import java.io.File;
import meli.mutantdetector.api.MutantDetectorAPI;
import meli.mutantdetector.api.database.DatabaseConnectionInfo;
import meli.mutantdetector.api.database.DatabaseVendorInfo;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Configurations {

    private static Logger _logger = Logger.getLogger(MutantDetectorAPI.class);
    
    private int _port;
    private int _threads;
    private DatabaseConnectionInfo _databaseConnectionInfo;
    private DatabaseVendorInfo _dataVendorInfo;
    
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
        if (Ace.assigned(config)) {
            if (config.has("port") && config.has("threads") && config.has("database")) {
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
        
        final JsonObject databaseConfig = Json.obtainJsonObject(config, "database");
        final String host = Json.obtainString(databaseConfig, "host");
        final int port = Json.obtainInteger(databaseConfig, "port");
        final String name = Json.obtainString(databaseConfig, "name");
        final String username = Json.obtainString(databaseConfig, "username");
        final String password = Json.obtainString(databaseConfig, "password");
        final String schema = Json.obtainString(databaseConfig, "schema");
        final String tablespaceData = Json.obtainString(databaseConfig, "tablespaceData");
        final String tablespaceIndexes = Json.obtainString(databaseConfig, "tablespaceIndexes");
        
        _databaseConnectionInfo = new DatabaseConnectionInfo(host, port, name, username, password, schema, tablespaceData, tablespaceIndexes);
        _dataVendorInfo = new DatabaseVendorInfo(DatabaseVendorInfo.MYSQL_VENDOR_NAME, DatabaseVendorInfo.MYSQL_URI_SCHEME, DatabaseVendorInfo.MYSQL_DRIVER_NAME);
        
        return true;
    }
    
    public int getPort() {
        return _port;
    }
    
    public int getThreads() {
        return _threads;
    }
    
    public DatabaseConnectionInfo getDatabaseConnectionInfo() {
        return _databaseConnectionInfo;
    }
    
    public DatabaseVendorInfo getDatabaseVendorInfo() {
        return _dataVendorInfo;
    }

}
