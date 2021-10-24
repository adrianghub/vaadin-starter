package com.kodilla.project.expanser.ui;

import com.kodilla.project.expanser.backend.service.ExpanserService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Expenser CRM")
public class DashboardView extends VerticalLayout {
    private ExpanserService expanserService;
}
