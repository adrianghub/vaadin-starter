package com.kodilla.project.expanser.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@PWA(name = "Expanse Tracker", shortName = "Expanse Tracker", enableInstallPrompt = false)
@PageTitle("Expanse Tracker")
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
    }
}
