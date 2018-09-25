/* AMCO @ 2018 */
package meli.mutantdetector.api;

import java.io.IOException;
import meli.mutantdetector.api.database.DatabaseConnectionInfo;
import meli.mutantdetector.api.database.DatabaseManager;
import meli.mutantdetector.api.database.DatabaseVendorInfo;
import meli.mutantdetector.api.server.MutantDetectorHttpHost;
import meli.mutantdetector.api.utils.Configurations;
import meli.mutantdetector.api.utils.Constants;
import org.apache.log4j.Logger;

public class Application {

    private static Logger _logger = Logger.getLogger(MutantDetectorAPI.class);

    public static void main(final String[] args) throws IOException {
        if (args.length > 0 && (args[0].equals(Constants.V_ARG) || args[0].equals(Constants.VERSION_ARG))) {
            System.out.println(Constants.INIT_MESSAGE);
            System.exit(0);
        }
        final Configurations configurations = new Configurations();
        if (configurations.setUpLog() && configurations.loadApiConfig()) {

            final int port = configurations.getPort();
            final int threads = configurations.getThreads();

            final DatabaseConnectionInfo databaseConnectionInfo = configurations.getDatabaseConnectionInfo();
            final DatabaseVendorInfo databaseVendorInfo = configurations.getDatabaseVendorInfo();

            final DatabaseManager databaseManager = new DatabaseManager(databaseVendorInfo, databaseConnectionInfo);
            if (!databaseManager.isConnected()) {
                System.exit(-1);
            }

            final MutantDetectorHttpHost httpHost = new MutantDetectorHttpHost(port, threads, false, null);
            httpHost.setName("");
            if (httpHost.hadException()) {
                _logger.error("Error initializing server.", (Exception) httpHost.getLastException());
                System.exit(-1);
            }
            final MutantDetectorAPI api = new MutantDetectorAPI(httpHost, databaseManager);

            _logger.info(Constants.INIT_MESSAGE);
            _logger.info("Application start.");

            api.start();
        } else {
            System.exit(-1);
        }
    }

}
