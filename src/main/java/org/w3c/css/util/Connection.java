package org.w3c.css.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
 * @author betehess
 * 
 * Abstracts the HTTP connection to a URL
 *
 */
public abstract class Connection {
	
	public abstract InputStream getBody() throws IOException;
	
	public abstract Map<String,List<String>> getHeaders() throws IOException;

	public abstract URL getURL();
	
	public abstract String getHeaderField(String name);
	
	public abstract String getContentType();
	
	public abstract String getContentEncoding();
	
	public abstract boolean isHttpURL();
	
	/** wraps a URLConnection */
	public static Connection fromURLConnection(final URLConnection conn) {
		return new Connection() {
			
			@Override
			public Map<String, List<String>> getHeaders() throws IOException {
				return conn.getHeaderFields();
			}
			
			@Override
			public InputStream getBody() throws IOException {
				return conn.getInputStream();
			}

			@Override
			public URL getURL() {
				return conn.getURL();
			}

			@Override
			public String getHeaderField(String name) {
				return conn.getHeaderField(name);
			}

			@Override
			public String getContentType() {
				return conn.getContentType();
			}

			@Override
			public String getContentEncoding() {
				return conn.getContentEncoding();
			}

			@Override
			public boolean isHttpURL() {
				return conn instanceof HttpURLConnection;
			}
		};
		
	}
	
}