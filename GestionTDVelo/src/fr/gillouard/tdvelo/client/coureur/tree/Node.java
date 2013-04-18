package fr.gillouard.tdvelo.client.coureur.tree;

import java.io.Serializable;
import java.util.List;

import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.TreeStore.TreeNode;

public class Node implements Serializable,
		TreeStore.TreeNode<Node> {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String categorie;

	protected Node() {

	}

	public Node(Integer id, String name, String categorie) {
		this.id = id;
		this.name = name;
		this.categorie = categorie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public Node getData() {
		return this;
	}

	@Override
	public List<? extends TreeNode<Node>> getChildren() {
		return null;
	}

	@Override
	public String toString() {
		return name != null ? name : super.toString();
	}

}
