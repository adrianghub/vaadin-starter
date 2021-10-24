package com.kodilla.project.xpanser.ui;

import com.kodilla.project.xpanser.backend.service.XpanserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Xpanser")
public class DashboardView extends VerticalLayout {
    private XpanserService service;

    public DashboardView(XpanserService service) {
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getProductStats(), getShopStats(), getCategoryStats());
    }

    private Component getProductStats() {
        Span stats = new Span(service.countProducts() + " products");
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Component getShopStats() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        service.findAllShops().forEach(shop -> {
            dataSeries.add(new DataSeriesItem(shop.getName(), shop.getProductCount()));
        });
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }

    private Component getCategoryStats() {
        Chart chart = new Chart(ChartType.BAR);

        DataSeries dataSeries = new DataSeries();
        service.findAllCategories().forEach(category -> {
            dataSeries.add(new DataSeriesItem(category.getName(), category.getTotalSpent()));
        });
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}
