/**
 * 
 */
package com.strandls.cca.controller;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * 
 * @author vilay
 *
 */
public class CCAControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CCADataController.class).in(Scopes.SINGLETON);
		bind(CCAMetaDataController.class).in(Scopes.SINGLETON);
	}
}