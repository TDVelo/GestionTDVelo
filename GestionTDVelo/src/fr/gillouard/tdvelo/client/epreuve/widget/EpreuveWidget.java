package fr.gillouard.tdvelo.client.epreuve.widget;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

import fr.gillouard.tdvelo.client.GestionTDVeloUtils;
import fr.gillouard.tdvelo.client.epreuve.service.EpreuveService;
import fr.gillouard.tdvelo.client.epreuve.service.EpreuveServiceAsync;
import fr.gillouard.tdvelo.client.events.ResultatEvent;
import fr.gillouard.tdvelo.client.events.ResultatEventHandler;
import fr.gillouard.tdvelo.shared.Epreuve;

public class EpreuveWidget {
	
	ContentPanel panel = new ContentPanel();
	TextField discipline1;
	TextField discipline2;
	TextField discipline3;
	TextField penalite2;
	String categorie;
	int dossard = -1;
	final DateTimeFormat fmt = DateTimeFormat.getFormat("mm,ss,SSS");
	String temps1 = "";
	String temps2 = "";
	String classement = "";
	String penalite = "";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final EpreuveServiceAsync epreuveService = GWT
			.create(EpreuveService.class);
	
	public EpreuveWidget() {
		
		GestionTDVeloUtils.EVENT_BUS.addHandler(ResultatEvent.TYPE,
				new ResultatEventHandler() {
					@Override
					public void onResultat(ResultatEvent resultatEvent) {
						dossard = resultatEvent.getDossard();
						categorie = resultatEvent.getCategorie();
						draw();
						epreuveService
								.getListeEpreuve(resultatEvent.getDossard(), new AsyncCallback<List<Epreuve>>() {
									@Override
									public void onSuccess(List<Epreuve> arg0) {
										for(Epreuve epreuve : arg0) {
											if("Route".equals(epreuve.getDiscipline())) {
												classement = String.valueOf(epreuve.getClassement());
												discipline3.setText(classement);
											} else if ("Cyclo-cross".equals(epreuve.getDiscipline())) {
												temps2 = fmt.format(new Date(epreuve.getTemps()));
												discipline2.setText(temps2);
											}else if("Adresse".equals(epreuve.getDiscipline())) {
												temps2 = fmt.format(new Date(epreuve.getTemps()));
												discipline2.setText(temps2);
												if(epreuve.getPenalite()>0) {
													penalite = fmt.format(new Date(epreuve.getPenalite()));
													penalite2.setText(penalite);
												}
											}else if("Vitesse".equals(epreuve.getDiscipline())) {
												temps1 = fmt.format(new Date(epreuve.getTemps()));
												discipline1.setText(temps1);
											}
										}
									}

									@Override
									public void onFailure(Throwable arg0) {
										Window.alert("Erreur lors de la recuperation des epreuves !");
									}
								});
					}
				});		
	}
	
	public Widget asWidget() {
		draw();
	    return this.panel;
	}
	
	private void draw() {
		this.panel.clear();
		this.panel.setWidth(500);
		
		this.panel.setBodyStyle("padding: 5px;");
		this.panel.setHeaderVisible(false);
	    
	    VerticalLayoutContainer c = new VerticalLayoutContainer();
	    discipline1 = new TextField();
	    FieldLabel fl1 = new FieldLabel(discipline1, "Vitesse (m,s,ms) ");
	    fl1.setLabelWidth(140);
	    c.add(fl1, new VerticalLayoutData(1, -1));
	    discipline2 = new TextField();
	    if(("poussin").equals(categorie)) {
	    	FieldLabel fl2 = new FieldLabel(discipline2, "Adresse (m,s,ms) ");
	    	fl2.setWidth(250);
	    	HBoxLayoutContainer hblc = new HBoxLayoutContainer();
	    	hblc.setPadding(new Padding(0, 0, 5, 0));
	    	hblc.setEnableOverflow(false);
	    	hblc.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
	    	hblc.add(fl2, new BoxLayoutData(new Margins(0, 5, 0, 0)));
	    	fl2.setLabelWidth(140);
	        penalite2 = new TextField();
	        FieldLabel fl3 = new FieldLabel(penalite2, "Penalités (m,s,ms) ");
	        fl3.setLabelWidth(130);
	    	fl3.setWidth(233);
	    	hblc.add(fl3);
	    	c.add(hblc);
	    } else {
	    	FieldLabel fl2 = new FieldLabel(discipline2, "Cyclo-cross (m,s,ms) ");
	    	fl2.setLabelWidth(140);
		    c.add(fl2, new VerticalLayoutData(1, -1));
	    }
	    discipline3 = new TextField();
	    FieldLabel fl3 = new FieldLabel(discipline3, "Route (Classement) ");
	    fl3.setLabelWidth(140);
	    c.add(fl3, new VerticalLayoutData(1, -1));
	    
    	if(dossard != -1) {
    		HBoxLayoutContainer hblc = new HBoxLayoutContainer();
    	    hblc.setPadding(new Padding(20, 10, 10, 400));
        	hblc.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
        	
        	ToggleButton tb = new ToggleButton("Enregistrer");
    		tb.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
    			public void onValueChange(ValueChangeEvent<Boolean> event) {
    				if(event.getValue()) {
    					changeEpreuve();
    				}
    			}        
    		});
    		hblc.add(tb);
    		c.add(hblc);
    	}
    	
    	this.panel.add(c);
	}
	
	private void changeEpreuve() {
		try {
			
			if(!"".equals(discipline1.getText())) {
				if("".equals(temps1)) {
					callChangeEpreuveService(true, getEpreuve(dossard, "Vitesse", fmt.parse(discipline1.getText()).getTime(), 0, 0));
				} else if(!temps1.equals(discipline1.getText())) {
						callChangeEpreuveService(false, getEpreuve(dossard, "Vitesse", fmt.parse(discipline1.getText()).getTime(), 0, 0));
					}
			}
			
			if(!"".equals(discipline2.getText()) && penalite2 == null) {
				if("".equals(temps2)) {
					callChangeEpreuveService(true, getEpreuve(dossard, "Cyclo-cross", fmt.parse(discipline2.getText()).getTime(), 0, 0));
				} else if(!temps2.equals(discipline2.getText())) {
						callChangeEpreuveService(false, getEpreuve(dossard, "Cyclo-cross", fmt.parse(discipline2.getText()).getTime(), 0, 0));
					}
			}
			if(!"".equals(discipline2.getText()) && penalite2 != null) {
				if("".equals(temps2)) {
					if("".equals(penalite2.getText())) {
						callChangeEpreuveService(true, getEpreuve(dossard, "Adresse", fmt.parse(discipline2.getText()).getTime(), 0, 0));
					} else {
						callChangeEpreuveService(true, getEpreuve(dossard, "Adresse", fmt.parse(discipline2.getText()).getTime(), fmt.parse(penalite2.getText()).getTime(), 0));
					}
				} else if(!temps2.equals(discipline2.getText()) || !penalite.equals(penalite2.getText())) {
						if("".equals(penalite2.getText())) {
							callChangeEpreuveService(false, getEpreuve(dossard, "Adresse", fmt.parse(discipline2.getText()).getTime(), 0, 0));
						} else {
							callChangeEpreuveService(false, getEpreuve(dossard, "Adresse", fmt.parse(discipline2.getText()).getTime(), fmt.parse(penalite2.getText()).getTime(), 0));
						}
					}
			}
			if(!"".equals(discipline3.getText())) {
				if("".equals(classement)) {
					callChangeEpreuveService(true, getEpreuve(dossard, "Route", 0, 0, Integer.valueOf(discipline3.getText())));
				} else if(!classement.equals(discipline3.getText())) {
						callChangeEpreuveService(false, getEpreuve(dossard, "Route", 0, 0, Integer.valueOf(discipline3.getText())));
					}	
			}
			
		}catch(final Exception e) {
			Window.alert("Erreur lors d'un ajout de resultat au dossard " + dossard + " " + e.getMessage());
		}
	}
	
	private void callChangeEpreuveService(final boolean insert, final Epreuve epreuve) {
		epreuveService.changeEpreuve(insert, epreuve, new AsyncCallback<Void>() {

		@Override
		public void onSuccess(Void arg0) {
			Info.display("Message", "Données mises à jour !");
		}

		@Override
		public void onFailure(Throwable arg0) {
			Window.alert("Erreur lors de l'ajout de l'epreuve !");
		}
		});
	}
	
	private Epreuve getEpreuve(final int dossard, final String discipline, final long temps, final long penalite, final int classement) {
		Epreuve epreuve = new Epreuve();
		epreuve.setDossard(dossard);
		epreuve.setDiscipline(discipline);
		epreuve.setTemps(temps);
		epreuve.setPenalite(penalite);
		epreuve.setClassement(classement);
		return epreuve;
	}
}
