package fr.gillouard.tdvelo.client.epreuve.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.gillouard.tdvelo.shared.Epreuve;

public interface EpreuveServiceAsync {
	void getListeEpreuve(final AsyncCallback<List<Epreuve>> callback)
			throws IllegalArgumentException;

}
