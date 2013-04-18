/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package fr.gillouard.tdvelo.client.coureur.tree;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Group extends Node {

  private List<Node> children = new ArrayList<Node>();

  protected Group() {

  }

  public Group(Integer id, String name) {
    super(id, name, "");
  }

  public List<Node> getChildren() {
    return children;
  }

  public void setChildren(List<Node> children) {
    this.children = children;
  }

  public void addChild(Node child) {
    getChildren().add(child);
  }
}
