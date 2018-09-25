package meli.mutantdetector.api.handler.post;

import ace.gson.Json;
import ace.gson.model.JsonModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import meli.mutantdetector.MutantDetector;
import whiz.net.HttpStatus;
import whiz.net.servers.HttpRequest;

public class MutantDetectorHandler extends LocalPostHandler {

    public MutantDetectorHandler(final String route) {
        super(MutantDetectorHandler.class, route, route);
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
        if (mutantDetector.isMutant(dna)) {
            request.replyOk();
            return null;
        }
        request.reply(HttpStatus.FORBIDDEN);
        return null;
    }

}
