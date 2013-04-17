package fr.gillouard.tdvelo.client.coureur.tree;

import com.sencha.gxt.core.client.ValueProvider;

public class NodeValueProvider implements ValueProvider<Node, String> {

	@Override
	public String getValue(Node object) {
		return object.getName();
	}

	@Override
	public void setValue(Node object, String value) {
	}

	@Override
	public String getPath() {
		return "name";
	}

}
