package fr.gillouard.tdvelo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class GestionTDVeloUtils {
	
	public static EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);

}
