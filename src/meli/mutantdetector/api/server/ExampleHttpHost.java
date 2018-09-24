package meli.mutantdetector.api.server;

import com.sun.net.httpserver.HttpsConfigurator;
import meli.mutantdetector.api.ExampleAPI;
import meli.mutantdetector.api.http.HttpHost;
import org.apache.log4j.Logger;

public class ExampleHttpHost extends HttpHost {
    
    private static Logger _logger = Logger.getLogger(ExampleAPI.class);
    
    public ExampleHttpHost(final int port, final int threads, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
        super(port, threads, autostart, httpsConfigurator);
    }

    @Override
    public void onStartListening() {
        _logger.info("Inicializando servidor.");
    }

    @Override
    public void onStopListening() {
        _logger.info("Finalizando servidor.");
    }
}
