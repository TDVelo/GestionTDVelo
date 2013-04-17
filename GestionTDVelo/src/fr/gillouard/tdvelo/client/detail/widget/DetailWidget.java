package fr.gillouard.tdvelo.client.detail.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.TextField;

import fr.gillouard.tdvelo.client.GestionTDVeloUtils;
import fr.gillouard.tdvelo.client.coureur.service.CoureurService;
import fr.gillouard.tdvelo.client.coureur.service.CoureurServiceAsync;
import fr.gillouard.tdvelo.client.events.DetailEvent;
import fr.gillouard.tdvelo.client.events.DetailEventHandler;
import fr.gillouard.tdvelo.shared.Coureur;

public class DetailWidget {
	
	interface MyUiBinder extends UiBinder<Widget, DetailWidget> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	TextField dossard;
	
	@UiField
	TextField nom;
	
	@UiField
	TextField prenom;
	
	@UiField
	TextField sexe;
	
	@UiField
	TextField club;
	
	@UiField
	TextField categorie;
	
	@UiField
	TextField equipe;
	
	private final CoureurServiceAsync coureurService = GWT
			.create(CoureurService.class);
	
	public Widget asWidget() {
		
		GestionTDVeloUtils.EVENT_BUS.addHandler(DetailEvent.TYPE, new DetailEventHandler()     {
	        
			@Override
			public void onDetailDemand(DetailEvent detailEvent) {
				coureurService.getDetailCoureur(detailEvent.getDossard(), new AsyncCallback<Coureur>() {
					public void onFailure(Throwable caught) {
						Window.alert("Erreur lors de la recuperation du detail du coureur !");
					}

					public void onSuccess(Coureur coureur) {
						try {
							dossard.setText(String.valueOf(coureur.getDossard()));
							nom.setText(coureur.getNom());
							prenom.setText(coureur.getPrenom());
							if(coureur.isSexe()) {
								sexe.setText("M");
							} else {
								sexe.setText("F");
							}
							club.setText(coureur.getClub());
							categorie.setText(coureur.getCategorie());
							equipe.setText(coureur.getEquipe());

						} catch (final Exception e) {
							Window.alert(e.getMessage());
						}
					}
				});
				
			}
	    });

		return uiBinder.createAndBindUi(this);
	}
	
	
}
