package fr.gillouard.tdvelo.client.desktop.widget;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;

public class DesktopWidget implements IsWidget {

	@Override
	public Widget asWidget() {

		final HorizontalLayoutContainer con = new HorizontalLayoutContainer();
		con.setPixelSize(1020, 750);

		ContentPanel cp1 = new ContentPanel();
		cp1.setHeadingText("Coureurs");
		cp1.setPixelSize(500, 700);
		cp1.setWidget(new HTML("This is an HTML Widget in a ContentPanel."));

		con.add(cp1);
		ContentPanel cp2 = new ContentPanel();
		cp2.setHeadingText("Details");
		cp2.setPixelSize(500, 700);
		cp2.setWidget(new HTML("This is an HTML Widget in a ContentPanel."));

		con.add(cp2);

		return con;
	}

}
