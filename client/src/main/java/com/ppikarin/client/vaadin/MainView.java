package com.ppikarin.client.vaadin;

import com.ppikarin.client.core.ClientService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    private final ClientService clientService;

    public MainView(ClientService clientService) {
        this.clientService = clientService;

        add(new Text(clientService.getAllStats().toString()));
    }

}
