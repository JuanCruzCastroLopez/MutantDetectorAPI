/* AMCO @ 2018 */
package meli.mutantdetector.api;

import meli.mutantdetector.api.handler.MutantDetectorHandler;
import meli.mutantdetector.api.server.ExampleHttpHost;

public class ExampleAPI {

    private final ExampleHttpHost _httpHost;
    
    public ExampleAPI(final ExampleHttpHost httpHost) {
        _httpHost = httpHost;
    }
    
    public void start() {
        _httpHost.registerHandler(new MutantDetectorHandler("/api/mutant"));
        _httpHost.start();
    }

}
