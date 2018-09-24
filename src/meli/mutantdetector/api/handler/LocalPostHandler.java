package meli.mutantdetector.api.handler;

import meli.mutantdetector.api.http.HttpResponse;
import ace.gson.builders.JsonObjectBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import meli.mutantdetector.api.MutantDetectorAPI;
import meli.mutantdetector.api.http.HttpRequest;
import meli.mutantdetector.api.http.RestJsonPostHandler;
import org.apache.log4j.Logger;

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

    private HttpResponse logResponse(final HttpRequest request, final String requestPath, final HttpResponse response) {
        _logger.trace("API Response: " + requestPath + " : " + response.toString());
        return response;
    }

    @Override
    protected HttpResponse onPost(final HttpRequest request, final JsonObject body, final JsonObject parameters) {
        final String requestPath = request.getRequestPath();
        final JsonObject result = execute(request, body, parameters);
        
        final HttpResponse httpResponse = new HttpResponse(result);
        
        return logResponse(request, requestPath, httpResponse);
    }

    @Override
    protected HttpResponse onBadMessageField(final HttpRequest hr, final JsonElement je, final String field) {
        _logger.error("onBadMessageField: " + field);
        final JsonObject result = formatResponseMessage(-1, "onBadMessageField: " + field);
        return new HttpResponse(result);
    }

    @Override
    protected HttpResponse onBadMessage(HttpRequest hr, JsonElement je) {
        _logger.error("onBadMessage: " + je.getAsString());
        final JsonObject result = formatResponseMessage(-2, "onBadMessage: " + je.getAsString());
        return new HttpResponse(result);
    }

    @Override
    protected HttpResponse onBadUrl(final HttpRequest hr, final String path) {
        _logger.error("onBadUrl: " + path);
        final JsonObject result = formatResponseMessage(-3, "onBadUrl: " + path);
        return new HttpResponse(result);
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
