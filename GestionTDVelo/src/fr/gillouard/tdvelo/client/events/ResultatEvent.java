package fr.gillouard.tdvelo.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ResultatEvent extends GwtEvent<ResultatEventHandler> {

	private int dossard;

	public ResultatEvent(final int dossard) {
		this.dossard = dossard;
	}

	public static Type<ResultatEventHandler> TYPE = new Type<ResultatEventHandler>();

	@Override
	public Type<ResultatEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ResultatEventHandler handler) {
		handler.onResultat(this);
	}

	/**
	 * @return the dossard
	 */
	public int getDossard() {
		return dossard;
	}
}