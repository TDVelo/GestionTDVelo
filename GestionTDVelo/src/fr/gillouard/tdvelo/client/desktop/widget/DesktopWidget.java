package fr.gillouard.tdvelo.client.desktop.widget;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;

import fr.gillouard.tdvelo.client.coureur.widget.CoureurWidget;
import fr.gillouard.tdvelo.client.detail.widget.DetailWidget;

public class DesktopWidget implements IsWidget {

	@Override
	public Widget asWidget() {

		final CoureurWidget coureurWid = new CoureurWidget();
		final DetailWidget detailWid = new DetailWidget();
		
		final HorizontalLayoutContainer con = new HorizontalLayoutContainer();
		con.setPixelSize(1020, 750);

		ContentPanel cp1 = new ContentPanel();
		cp1.setHeadingText("Coureurs");
		cp1.setPixelSize(500, 700);
		cp1.setWidget(coureurWid.asWidget());

		con.add(cp1);
		ContentPanel cp2 = new ContentPanel();
		cp2.setHeadingText("Details");
		cp2.setPixelSize(500, 700);
		cp2.setWidget(detailWid.asWidget());

		con.add(cp2);

		return con;
	}

}
