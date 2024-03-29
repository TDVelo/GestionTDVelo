package fr.gillouard.tdvelo.client.epreuve.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.gillouard.tdvelo.shared.Epreuve;

@RemoteServiceRelativePath("epreuve")
public interface EpreuveService extends RemoteService {

	List<Epreuve> getListeEpreuve(final int dossard) throws IllegalArgumentException;

	void changeEpreuve(boolean insert, Epreuve epreuve) throws Exception;
}
