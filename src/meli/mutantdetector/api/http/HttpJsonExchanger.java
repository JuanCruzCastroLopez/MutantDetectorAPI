package meli.mutantdetector.api.http;

import com.google.gson.JsonElement;

interface HttpJsonExchanger {

    HttpResponse exchange(HttpRequest request, JsonElement json);

}
