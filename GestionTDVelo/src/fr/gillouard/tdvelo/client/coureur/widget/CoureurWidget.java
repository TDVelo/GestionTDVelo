package fr.gillouard.tdvelo.client.coureur.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.tree.Tree;

import fr.gillouard.tdvelo.client.coureur.tree.KeyProvider;
import fr.gillouard.tdvelo.client.coureur.tree.Node;
import fr.gillouard.tdvelo.client.coureur.tree.NodeStoreFilterField;
import fr.gillouard.tdvelo.client.coureur.tree.NodeValueProvider;

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
	
	public Widget asWidget(final TreeStore<Node> store) {
		
		this.store = store;
		Widget widget = uiBinder.createAndBindUi(this);
		filter.bind(store);
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
