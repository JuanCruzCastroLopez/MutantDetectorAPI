package meli.mutantdetector.api;

import meli.mutantdetector.api.database.DatabaseManager;
import meli.mutantdetector.api.handler.get.MutantStatsHandler;
import meli.mutantdetector.api.handler.post.MutantDetectorHandler;
import meli.mutantdetector.api.server.MutantDetectorHttpHost;

public class MutantDetectorAPI {

    private final MutantDetectorHttpHost _httpHost;
    private final DatabaseManager _databaseManager;
    
    public MutantDetectorAPI(final MutantDetectorHttpHost httpHost, final DatabaseManager databaseManager) {
        _httpHost = httpHost;
        _databaseManager = databaseManager;
    }
    
    public void start() {
        _httpHost.registerHandler(new MutantDetectorHandler("/mutant/"));
        _httpHost.registerHandler(new MutantStatsHandler("/stats", "/stats", _databaseManager));
        _httpHost.start();
    }

}
