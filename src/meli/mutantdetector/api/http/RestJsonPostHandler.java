
package meli.mutantdetector.api.http;

import ace.gson.model.JsonModel;
import ace.interfaces.Treater;
import com.google.gson.JsonObject;
import whiz.net.HttpMethod;

public abstract class RestJsonPostHandler extends RestJsonAbstractHandler {

	protected JsonModel _modelPost;

	public RestJsonPostHandler(final Class<?> clazz, final String route, final String template) {
		this(clazz, route, template, null, null);
	}

	public RestJsonPostHandler(final Class<?> clazz, final String route, final String template, final Treater<byte[]> readingAdapter, final Treater<byte[]> writingAdapter) {
		super(clazz, new HttpMethod[] { HttpMethod.POST }, route, template, readingAdapter, writingAdapter);
		setupModel();
	}

	protected final void setupModel() {
		_modelPost = modelPost();
	}

	@Override final JsonModel getModel(final HttpRequest request) {
		return _modelPost;
	}

	@Override final HttpResponse derive(final HttpRequest request, final JsonObject body, final JsonObject parameters) {
		return onPost(request, body, parameters);
	}

	protected abstract JsonModel modelPost();

	protected abstract HttpResponse onPost(final HttpRequest request, final JsonObject body, final JsonObject parameters);

}
