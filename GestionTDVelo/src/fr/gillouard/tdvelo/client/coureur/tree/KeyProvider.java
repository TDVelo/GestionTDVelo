package fr.gillouard.tdvelo.client.coureur.tree;

import com.sencha.gxt.data.shared.ModelKeyProvider;

public class KeyProvider implements ModelKeyProvider<Node> {
	@Override
	public String getKey(Node item) {
		return (item instanceof Group ? "g-" : "n-")
				+ item.getId().toString();
	}
}
