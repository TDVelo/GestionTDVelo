package fr.gillouard.tdvelo.client.desktop.widget;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

import fr.gillouard.tdvelo.client.coureur.widget.CoureurWidget;
import fr.gillouard.tdvelo.client.detail.widget.DetailWidget;
import fr.gillouard.tdvelo.client.epreuve.widget.EpreuveWidget;

public class DesktopWidget implements IsWidget {

	@Override
	public Widget asWidget() {

		final CoureurWidget coureurWid = new CoureurWidget();
		final DetailWidget detailWid = new DetailWidget();
		final EpreuveWidget epreuveWid = new EpreuveWidget();

		//wid.asWidget();
		final HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
		hlc.setPixelSize(1020, 750);

		final ContentPanel cp1 = new ContentPanel();
		cp1.setHeadingText("Coureurs");
		cp1.setPixelSize(500, 700);
		cp1.setWidget(coureurWid.asWidget());
		hlc.add(cp1);

		final VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		final ContentPanel cp2 = new ContentPanel();
		cp2.setHeadingText("Details");
		cp2.setPixelSize(500, 350);
		cp2.setWidget(detailWid.asWidget());
		vlc.add(cp2);
		
		final ContentPanel cp3 = new ContentPanel();
		cp3.setHeadingText("Epreuves");
		cp3.setPixelSize(500, 350);
		cp3.setWidget(epreuveWid.asWidget());
		vlc.add(cp3);

		hlc.add(vlc);

		return hlc;
	}

}
