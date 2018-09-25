package meli.mutantdetector.api.handler.get;

import ace.gson.Json;
import com.google.gson.JsonObject;
import meli.mutantdetector.api.MutantDetectorAPI;
import meli.mutantdetector.api.database.DatabaseManager;
import meli.mutantdetector.api.model.Stadistic;
import org.apache.log4j.Logger;
import whiz.net.servers.HttpRequest;

public class MutantStatsHandler extends AbstractHttpHandler {
    
    private static Logger _logger = Logger.getLogger(MutantDetectorAPI.class);

    private final DatabaseManager _databaseManager;

    public MutantStatsHandler(final String route, final String template, final DatabaseManager databaseManager) {
        super(MutantStatsHandler.class, route, template);
        _databaseManager = databaseManager;
    }

    @Override
    protected JsonObject onGet(HttpRequest request, final JsonObject body, final JsonObject parameters) {
        _logger.trace("Request body: " + Json.JsonElementToString(body));
        _logger.trace("Request parameters: " + Json.JsonElementToString(parameters));
        
        final Stadistic stadistic = new Stadistic();

        _databaseManager.executeSelect(stadistic);

        return stadistic.toJsonObject();
    }

}
