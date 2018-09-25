package meli.mutantdetector.api.handler.get;

import com.google.gson.JsonObject;
import meli.mutantdetector.api.database.DatabaseManager;
import meli.mutantdetector.api.model.Stadistic;
import whiz.net.servers.HttpRequest;

public class MutantStatsHandler extends AbstractHttpHandler {

    private final DatabaseManager _databaseManager;

    public MutantStatsHandler(final String route, final String template, final DatabaseManager databaseManager) {
        super(MutantStatsHandler.class, route, template);
        _databaseManager = databaseManager;
    }

    @Override
    protected JsonObject onGet(HttpRequest request, final JsonObject body, final JsonObject parameters) {
        final Stadistic stadistic = new Stadistic();
        
        _databaseManager.executeSelect(stadistic);
        
        return stadistic.toJsonObject();
    }

}
