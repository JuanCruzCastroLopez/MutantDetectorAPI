/* AMCO @ 2018 */
package meli.mutantdetector.api;

import java.io.IOException;
import meli.mutantdetector.api.server.ExampleHttpHost;
import meli.mutantdetector.api.utils.Configurations;
import meli.mutantdetector.api.utils.Constants;
import org.apache.log4j.Logger;

public class Application {

    private static Logger _logger = Logger.getLogger(ExampleAPI.class);

    public static void main(final String[] args) throws IOException {
        if (args.length > 0 && (args[0].equals(Constants.V_ARG) || args[0].equals(Constants.VERSION_ARG))) {
            System.out.println(Constants.INIT_MESSAGE);
            System.exit(0);
        }
        final Configurations configurations = new Configurations();
        if (configurations.setUpLog() && configurations.loadApiConfig()) {
            
            final int port = configurations.getPort();
            final int threads = configurations.getThreads();
            
            final ExampleHttpHost httpHost = new ExampleHttpHost(port, threads, false, null);        //TODO: Poner configuración
            httpHost.setName("");
            if (httpHost.hadException()) {
                _logger.error("Error al inicializar el servidor.", (Exception) httpHost.getLastException());
                System.exit(-1);
            }
            final ExampleAPI api = new ExampleAPI(httpHost);

            _logger.info(Constants.INIT_MESSAGE);
            _logger.info("Application start.");
            
            api.start();
        } else {
            System.exit(-1);
        }
    }

}
