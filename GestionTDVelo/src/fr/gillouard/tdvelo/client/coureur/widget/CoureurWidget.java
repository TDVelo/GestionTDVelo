package fr.gillouard.tdvelo.client.coureur.widget;

import java.util.List;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.tree.Tree;

import fr.gillouard.tdvelo.client.GestionTDVeloUtils;
import fr.gillouard.tdvelo.client.coureur.service.CoureurService;
import fr.gillouard.tdvelo.client.coureur.service.CoureurServiceAsync;
import fr.gillouard.tdvelo.client.coureur.tree.Group;
import fr.gillouard.tdvelo.client.coureur.tree.KeyProvider;
import fr.gillouard.tdvelo.client.coureur.tree.Node;
import fr.gillouard.tdvelo.client.coureur.tree.NodeStoreFilterField;
import fr.gillouard.tdvelo.client.coureur.tree.NodeValueProvider;
import fr.gillouard.tdvelo.client.events.DetailEvent;
import fr.gillouard.tdvelo.shared.Coureur;

public class CoureurWidget {

	interface MyUiBinder extends UiBinder<Widget, CoureurWidget> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField(provided = true)
	TreeStore<Node> store = new TreeStore<Node>(new KeyProvider());

	@UiField(provided = true)
	StoreFilterField<Node> filter = new NodeStoreFilterField();

	@UiField
	Tree<Node, String> tree;

	@UiFactory
	public ValueProvider<Node, String> createValueProvider() {
		return new NodeValueProvider();
	}

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final CoureurServiceAsync coureurService = GWT
			.create(CoureurService.class);

	public Widget asWidget() {

		coureurService.getListeCoureur(new AsyncCallback<List<Coureur>>() {
			public void onFailure(Throwable caught) {
				Window.alert("Erreur lors de la recuperation de la liste des coureurs !");
			}

			public void onSuccess(List<Coureur> result) {
				try {
					String categorie = "";
					Group group = null;
					for (Coureur coureur : result) {
						if (!coureur.getCategorie().equals(categorie)) {
							categorie = coureur.getCategorie();
							// Attention ceci fera un bug si plus de 1000
							// participants
							group = new Group(coureur.getDossard() + 1000,
									coureur.getCategorie());
							store.add(group);
						}
						store.add(
								group,
								new Node(coureur.getDossard(), coureur
										.getDossard()
										+ " "
										+ coureur.getNom()
										+ " "
										+ coureur.getPrenom()
										+ " "
										+ coureur.getClub()));
					}

				} catch (final Exception e) {
					Window.alert(e.getMessage());
				}
			}
		});

		Widget widget = uiBinder.createAndBindUi(this);
		
		SimpleSafeHtmlCell<String> cell = new SimpleSafeHtmlCell<String>(
				SimpleSafeHtmlRenderer.getInstance(), "click") {
			@Override
			public void onBrowserEvent(Context context, Element parent,
					String value, NativeEvent event,
					ValueUpdater<String> valueUpdater) {
				super.onBrowserEvent(context, parent, value, event,
						valueUpdater);
				if ("click".equals(event.getType())) {
					final int dossard = Integer.valueOf(value.substring(0, value.indexOf(" ")));
					GestionTDVeloUtils.EVENT_BUS.fireEvent(new DetailEvent(dossard));
				}
				
			}
		};

		filter.bind(store);
		tree.setCell(cell);
		
		return ((Container) widget);
	}

	@UiHandler("expandAll")
	public void expandAll(SelectEvent event) {
		tree.expandAll();
	}

	@UiHandler("collapseAll")
	public void collapseAll(SelectEvent event) {
		tree.collapseAll();
	}
}
