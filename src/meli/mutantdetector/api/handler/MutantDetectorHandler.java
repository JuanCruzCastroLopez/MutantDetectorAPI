package meli.mutantdetector.api.handler;

import ace.gson.Json;
import ace.gson.model.JsonModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import meli.mutantdetector.api.http.HttpRequest;
import meli.mutantdetector.MutantDetector;

public class MutantDetectorHandler extends LocalPostHandler {

    public MutantDetectorHandler(final String route) {
        super(MutantDetectorHandler.class, route, route);
    }

    @Override
    protected JsonModel modelPost() {
        return new JsonModel().registerMandatoryNonEmptyArray("dna");
    }

    @Override
    protected JsonObject execute(HttpRequest request, JsonObject body, JsonObject parameters) {
        final JsonArray ja = Json.obtainJsonArray(body, "dna");
        final String[] dna = Json.convertJsonArraytoStringArray(ja);
        
        final MutantDetector mutantDetector = new MutantDetector();
        if (mutantDetector.isMutant(dna)) {
            return formatResponseMessage(0, "OK");
        }
        return formatResponseMessage(-1, "Forbidden");
    }

}
