package meli.mutantdetector.api;

import meli.mutantdetector.api.handler.MutantDetectorHandler;
import meli.mutantdetector.api.server.MutantDetectorHttpHost;

public class MutantDetectorAPI {

    private final MutantDetectorHttpHost _httpHost;
    
    public MutantDetectorAPI(final MutantDetectorHttpHost httpHost) {
        _httpHost = httpHost;
    }
    
    public void start() {
        _httpHost.registerHandler(new MutantDetectorHandler("/mutant/"));
        _httpHost.start();
    }

}
