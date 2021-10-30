package com.kodilla.project.xpanser.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

public class BottomMessagePanel {
    public static Component addBottomMessage(Paragraph message, RouterLink link, String className) {
        HorizontalLayout hl = new HorizontalLayout(message, link);
        hl.addClassName(className);
        hl.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        return hl;
    }
}
