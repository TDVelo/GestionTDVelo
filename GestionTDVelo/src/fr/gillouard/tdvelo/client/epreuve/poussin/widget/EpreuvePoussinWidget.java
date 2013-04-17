package fr.gillouard.tdvelo.client.epreuve.poussin.widget;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import fr.gillouard.tdvelo.client.GestionTDVeloUtils;
import fr.gillouard.tdvelo.client.epreuve.service.EpreuveService;
import fr.gillouard.tdvelo.client.epreuve.service.EpreuveServiceAsync;
import fr.gillouard.tdvelo.client.events.ResultatEvent;
import fr.gillouard.tdvelo.client.events.ResultatEventHandler;
import fr.gillouard.tdvelo.shared.Epreuve;

public class EpreuvePoussinWidget {

	interface MyUiBinder extends UiBinder<Widget, EpreuvePoussinWidget> {
	}

	private final EpreuveServiceAsync epreuveService = GWT
			.create(EpreuveService.class);

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	public Widget asWidget() {
		Epreuve epreuve = new Epreuve();
		epreuve.setClassement(1);
		epreuve.setDiscipline("Cyclo");
		epreuve.setDossard(51);
		epreuve.setPenalite(0);
		epreuve.setTemps(50.20);
		epreuve.setType("Temps-sanspenalite");

		GestionTDVeloUtils.EVENT_BUS.addHandler(ResultatEvent.TYPE,
				new ResultatEventHandler() {

					@Override
					public void onResultat(ResultatEvent resultatEvent) {
						epreuveService
								.getListeEpreuve(new AsyncCallback<List<Epreuve>>() {
									@Override
									public void onSuccess(List<Epreuve> arg0) {
										Window.alert("je suis "
												+ (((Epreuve) arg0.get(0))
														.getClassement()));
									}

									@Override
									public void onFailure(Throwable arg0) {
										Window.alert("Erreur lors dea la recuperation de l'epreuve !");
									}
								});
					}
				});

		epreuveService.ajouterEpreuve(epreuve, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void arg0) {
				GestionTDVeloUtils.EVENT_BUS.fireEvent(new ResultatEvent(51));
			}

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Erreur lors de l'ajout de l'epreuve !");
			}
		});

		Widget widget = uiBinder.createAndBindUi(this);

		return widget;
	}
}
