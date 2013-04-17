package fr.gillouard.tdvelo.client.coureur.tree;

import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;

public class NodeStoreFilterField extends StoreFilterField<Node> {
	
	@Override
	protected boolean doSelect(Store<Node> store,
			Node parent, Node item, String filter) {

		String name = item.getName();
		name = name.toLowerCase();
		if (name.startsWith(filter.toLowerCase())) {
			return true;
		}
		return false;
	}
	
}
