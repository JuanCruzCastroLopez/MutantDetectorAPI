package meli.mutantdetector.api.handler.post;

import ace.gson.builders.JsonObjectBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import meli.mutantdetector.api.MutantDetectorAPI;
import org.apache.log4j.Logger;
import whiz.net.servers.HttpRequest;
import whiz.net.servers.RestJsonPostHandler;

public abstract class LocalPostHandler extends RestJsonPostHandler {
    
    private static Logger _logger = Logger.getLogger(MutantDetectorAPI.class);

    public LocalPostHandler(final Class<?> clazz, final String route, final String template) {
        super(clazz, route, template);
    }

    protected JsonObject formatResponseMessage(final int code, final String description) {
        return new JsonObjectBuilder()
                .add("status", new JsonObjectBuilder()
                        .add("code", code)
                        .add("description", description))
                .getAsJsonObject();
    }

    @Override
    protected JsonObject onPost(final HttpRequest request, final JsonObject body, final JsonObject parameters) {
        return execute(request, body, parameters);
    }

    @Override
    protected JsonElement onBadMessageField(final HttpRequest hr, final JsonElement je, final String field) {
        _logger.error("onBadMessageField: " + field);
        return formatResponseMessage(-1, "onBadMessageField: " + field);
    }

    @Override
    protected JsonElement onBadMessage(HttpRequest hr, JsonElement je) {
        _logger.error("onBadMessage: " + je.getAsString());
        return formatResponseMessage(-2, "onBadMessage: " + je.getAsString());
    }

    @Override
    protected JsonElement onBadUrl(final HttpRequest hr, final String path) {
        _logger.error("onBadUrl: " + path);
        return formatResponseMessage(-3, "onBadUrl: " + path);
    }

    @Override
    protected void onWrongMethod(final HttpRequest hr, final String methodName) {
        _logger.error("onWrongMethod: " + methodName);
    }

    @Override
    protected void onClientException(final HttpRequest hr, final Throwable e, final byte[] bytes) {
        _logger.error("onClientException");
    }

    protected abstract JsonObject execute(final HttpRequest request, final JsonObject body, final JsonObject parameters);

}
