
package meli.mutantdetector.api.handler.get;

import ace.gson.builders.JsonObjectBuilder;
import com.google.gson.JsonElement;
import meli.mutantdetector.api.MutantDetectorAPI;
import org.apache.log4j.Logger;
import whiz.net.servers.HttpRequest;
import whiz.net.servers.RestJsonGetHandler;

public abstract class AbstractHttpHandler extends RestJsonGetHandler {
    
    private static Logger _logger = Logger.getLogger(MutantDetectorAPI.class);

    public AbstractHttpHandler(final Class clazz, final String route, final String template) {
	super(clazz, route, template);
    }

    @Override protected JsonElement onBadMessageField(HttpRequest hr, JsonElement je, String string) {
	return new JsonObjectBuilder().add("onBadMessageField", "onBadMessageField").getAsJsonObject();
    }

    @Override protected JsonElement onBadMessage(HttpRequest hr, JsonElement je) {
	return new JsonObjectBuilder().add("onBadMessage", "onBadMessage").getAsJsonObject();
    }

    @Override protected JsonElement onBadUrl(HttpRequest hr, String string) {
	return new JsonObjectBuilder().add("badurl", "badurl").getAsJsonObject();
    }

    @Override protected void onWrongMethod(HttpRequest hr, String string) {
        _logger.error("onWrongMethod: " + string);
    }

    @Override protected void onClientException(HttpRequest hr, Throwable thrwbl, byte[] bytes) {
    }
}
