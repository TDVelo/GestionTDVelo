package fr.gillouard.tdvelo.client.detail.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class DetailWidget {

	interface MyUiBinder extends UiBinder<Widget, DetailWidget> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	public Widget asWidget() {
		return uiBinder.createAndBindUi(this);
	}

}
