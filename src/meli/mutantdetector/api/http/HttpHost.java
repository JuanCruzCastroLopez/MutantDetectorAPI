
package meli.mutantdetector.api.http;

import ace.concurrency.Threads;
import com.sun.net.httpserver.HttpsConfigurator;
import java.net.InetSocketAddress;
import java.net.URL;
import whiz.net.URIBuilder;

public class HttpHost extends HttpStand {

	public static int DEFAULT_PORT = 80;
	public static int DEFAULT_THREADS = Threads.DEFAULT;

	public HttpHost() {
		this(HttpHost.class);
	}

	public HttpHost(final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz) {
		this(clazz, new InetSocketAddress(DEFAULT_PORT), DEFAULT_THREADS, DEFAULT_AUTOSTART);
	}

	public HttpHost(final Class<?> clazz, final HttpsConfigurator httpsConfigurator) {
		this(clazz, new InetSocketAddress(DEFAULT_PORT), DEFAULT_THREADS, DEFAULT_AUTOSTART, httpsConfigurator);
	}

	public HttpHost(final boolean autostart) {
		this(HttpHost.class, autostart);
	}

	public HttpHost(final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, autostart, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz, final boolean autostart) {
		this(clazz, new InetSocketAddress(DEFAULT_PORT), DEFAULT_THREADS, autostart);
	}

	public HttpHost(final Class<?> clazz, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		this(clazz, new InetSocketAddress(DEFAULT_PORT), DEFAULT_THREADS, autostart, httpsConfigurator);
	}

	public HttpHost(final int port) {
		this(HttpHost.class, port);
	}

	public HttpHost(final int port, final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, port, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz, final int port) {
		this(clazz, new InetSocketAddress(port), DEFAULT_THREADS, DEFAULT_AUTOSTART);
	}

	public HttpHost(final Class<?> clazz, final int port, final HttpsConfigurator httpsConfigurator) {
		this(clazz, new InetSocketAddress(port), DEFAULT_THREADS, DEFAULT_AUTOSTART, httpsConfigurator);
	}

	public HttpHost(final int port, final int threads) {
		this(HttpHost.class, port, threads);
	}

	public HttpHost(final int port, final int threads, final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, port, threads, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz, final int port, final int threads) {
		this(clazz, new InetSocketAddress(port), threads, DEFAULT_AUTOSTART);
	}

	public HttpHost(final Class<?> clazz, final int port, final int threads, final HttpsConfigurator httpsConfigurator) {
		this(clazz, new InetSocketAddress(port), threads, DEFAULT_AUTOSTART, httpsConfigurator);
	}

	public HttpHost(final int port, final boolean autostart) {
		this(HttpHost.class, port, autostart);
	}

	public HttpHost(final int port, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, port, autostart, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz, final int port, final boolean autostart) {
		this(clazz, new InetSocketAddress(port), DEFAULT_THREADS, autostart);
	}

	public HttpHost(final Class<?> clazz, final int port, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		this(clazz, new InetSocketAddress(port), DEFAULT_THREADS, autostart, httpsConfigurator);
	}

	public HttpHost(final int port, final int threads, final boolean autostart) {
		this(HttpHost.class, port, threads, autostart);
	}

	public HttpHost(final int port, final int threads, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, port, threads, autostart, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz, final int port, final int threads, final boolean autostart) {
		this(clazz, new InetSocketAddress(port), threads, autostart);
	}

	public HttpHost(final Class<?> clazz, final int port, final int threads, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		this(clazz, new InetSocketAddress(port), threads, autostart, httpsConfigurator);
	}

	public HttpHost(final InetSocketAddress address) {
		this(HttpHost.class, address);
	}

	public HttpHost(final InetSocketAddress address, final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, address, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz, final InetSocketAddress address) {
		this(clazz, address, DEFAULT_THREADS, DEFAULT_AUTOSTART);
	}

	public HttpHost(final Class<?> clazz, final InetSocketAddress address, final HttpsConfigurator httpsConfigurator) {
		this(clazz, address, DEFAULT_THREADS, DEFAULT_AUTOSTART, httpsConfigurator);
	}

	public HttpHost(final InetSocketAddress address, final int threads) {
		this(HttpHost.class, address, threads);
	}

	public HttpHost(final InetSocketAddress address, final int threads, final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, address, threads, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz, final InetSocketAddress address, final int threads) {
		this(clazz, address, threads, DEFAULT_AUTOSTART);
	}

	public HttpHost(final Class<?> clazz, final InetSocketAddress address, final int threads, final HttpsConfigurator httpsConfigurator) {
		this(clazz, address, threads, DEFAULT_AUTOSTART, httpsConfigurator);
	}

	public HttpHost(final InetSocketAddress address, final boolean autostart) {
		this(HttpHost.class, address, autostart);
	}

	public HttpHost(final InetSocketAddress address, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, address, autostart, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz, final InetSocketAddress address, final boolean autostart) {
		this(clazz, address, DEFAULT_THREADS, autostart);
	}

	public HttpHost(final Class<?> clazz, final InetSocketAddress address, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		this(clazz, address, DEFAULT_THREADS, autostart, httpsConfigurator);
	}

	public HttpHost(final InetSocketAddress address, final int threads, final boolean autostart) {
		this(HttpHost.class, address, threads, autostart);
	}

	public HttpHost(final InetSocketAddress address, final int threads, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		this(HttpHost.class, address, threads, autostart, httpsConfigurator);
	}

	public HttpHost(final Class<?> clazz, final InetSocketAddress address, final int threads, final boolean autostart) {
		this(clazz, address, threads, autostart, null);
	}

	public HttpHost(final Class<?> clazz, final InetSocketAddress address, final int threads, final boolean autostart, final HttpsConfigurator httpsConfigurator) {
		super(clazz, httpsConfigurator);
		setExecutionThreadsMaxCount(threads);
		if (initialize(address) && autostart) {
			start();
		}
	}

	@Override public HttpHost setName(final String value) {
		return (HttpHost) super.setName(value);
	}

	@Override public HttpHost setExecutionThreadsMaxCount(final Integer value) {
		return (HttpHost) super.setExecutionThreadsMaxCount(value);
	}

	public final boolean getCrossOriginRequestsAllowance() {
		return getCrossOriginAllowance();
	}

	public HttpHost setCrossOriginRequestsAllowance(final boolean value) {
		setCrossOriginAllowance(value);
		return this;
	}

	public URL getBaseURL() {
		return new URIBuilder()
			.setScheme(getURIScheme())
			.setHost(getHost())
			.setPort(getPort())
		.getAsURL();
	}

	@Override public void onStartListening() {
		// NOTE: override this in inherited classes to control the event
	}

	@Override public void onStopListening() {
		// NOTE: override this in inherited classes to control the event
	}

	public HttpsConfigurator onConfigureHttps() {
		// NOTE: override this in inherited classes to control the event
		return null;
	}

}