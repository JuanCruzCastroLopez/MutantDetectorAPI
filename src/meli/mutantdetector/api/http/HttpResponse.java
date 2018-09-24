/* AMCO @ 2018 */
package meli.mutantdetector.api.http;

import ace.gson.Json;
import ace.interfaces.Treater;
import com.google.gson.JsonObject;

public class HttpResponse {

    protected final JsonObject _response;
    private byte[] _byteResponse;

    public HttpResponse(final JsonObject response) {
        _response = response;
    }

    @Override
    public String toString() {
        return Json.JsonElementToString(_response);
    }

    public void setByteArray(final String charset) throws Throwable {
        _byteResponse = Json.JsonElementToString(_response).getBytes(charset);
    }

    public HttpResponse treat(final Treater<byte[]> _writingAdapter) {
        _byteResponse = _writingAdapter.treat(_byteResponse);
        return this;
    }

    public byte[] getAsByteArray() {
        return _byteResponse;
    }

    public boolean itsOk() {
        final JsonObject status = Json.obtainJsonObject(_response, "status");
        final int statusCode = Json.obtainInteger(status, "code");
        
        return statusCode == 0;
    }

}
