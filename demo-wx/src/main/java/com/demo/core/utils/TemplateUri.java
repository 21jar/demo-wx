package com.demo.core.utils;

import org.springframework.web.util.UriComponents.UriTemplateVariables;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class TemplateUri {
	public static TemplateUri fromUri(String uri){
		return new TemplateUri(uri);
	}
	
	private String uri;
	private String buildUri;
	private Map<String,String> variables = new HashMap<String,String>();
	public TemplateUri(String uri) {
		this.uri = uri;
	}

	public TemplateUri var(String name, String value) {
		if(buildUri != null) throw new IllegalStateException("expand after build");
		variables.put(name, value);
		return this;
	}

	public String build() {
		if(buildUri != null) return buildUri;
		return UriComponentsBuilder
				.fromUriString(uri)
				.build()
				.expand(new UriTemplateVariables(){
					@Override
					public Object getValue(String name) {
						if(variables.containsKey(name)) return variables.get(name);
						return resolveDefault(name);
					}
				})
				.encode().toUriString();			
	}
	
	protected Object resolveDefault(String name) {
		return UriTemplateVariables.SKIP_VALUE;
	}
	
	@Override
	public String toString() {
		return build();
	}
}
