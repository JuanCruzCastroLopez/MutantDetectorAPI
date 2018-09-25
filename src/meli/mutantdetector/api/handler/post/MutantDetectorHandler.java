package meli.mutantdetector.api.handler.post;

import ace.gson.Json;
import ace.gson.model.JsonModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import meli.mutantdetector.MutantDetector;
import meli.mutantdetector.api.database.DatabaseManager;
import meli.mutantdetector.api.model.DnaResult;
import whiz.net.HttpStatus;
import whiz.net.servers.HttpRequest;

public class MutantDetectorHandler extends LocalPostHandler {

    private final DatabaseManager _databaseManager;

    public MutantDetectorHandler(final String route, final DatabaseManager databaseManager) {
        super(MutantDetectorHandler.class, route, route);
        _databaseManager = databaseManager;
    }

    @Override
    protected JsonModel modelPost() {
        return new JsonModel().registerMandatoryArray("dna");
    }

    @Override
    protected JsonObject execute(HttpRequest request, JsonObject body, JsonObject parameters) {
        final JsonArray ja = Json.obtainJsonArray(body, "dna");
        final String[] dna = Json.convertJsonArraytoStringArray(ja);
        
        final MutantDetector mutantDetector = new MutantDetector();

        final DnaResult dnaResult;
        if (mutantDetector.isMutant(dna)) {
            dnaResult = new DnaResult(dna, true);
            request.replyOk();
        } else {
            request.reply(HttpStatus.FORBIDDEN);
            dnaResult = new DnaResult(dna, false);
        }
        _databaseManager.executeUpdate(dnaResult);
        return null;
    }

}
