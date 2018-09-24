package meli.mutantdetector.api.server;

import com.sun.net.httpserver.HttpsConfigurator;
import meli.mutantdetector.api.MutantDetectorAPI;
import meli.mutantdetector.api.http.HttpHost;
import org.apache.log4j.Logger;

public class MutantDetectorHttpHost extends HttpHost {
    
    private static Logger _logger = Logger.getLogger(MutantDetectorAPI.class);
    
    public MutantDetectorHttpHost(final int port, final int threads, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
        super(port, threads, autostart, httpsConfigurator);
    }

    @Override
    public void onStartListening() {
        _logger.info("Initializing server.");
    }

    @Override
    public void onStopListening() {
        _logger.info("Finalizing Server.");
    }
}
