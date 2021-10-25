package com.kodilla.project.xpanser.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;

@PageTitle("About | Xpanser")
public class AboutView extends VerticalLayout {

    public AboutView() {
        addClassName("about-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getContent());
    }

    private Component getContent() {
        H1 title = new H1("About");
        Paragraph content = new Paragraph("About the app.");

        VerticalLayout wrapper = new VerticalLayout(
                title,
                content
        );

        return wrapper;
    }
}

