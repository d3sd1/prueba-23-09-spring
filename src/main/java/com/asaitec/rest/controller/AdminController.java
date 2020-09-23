package com.asaitec.rest.controller;

import com.asaitec.rest.model.TicketStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * View current operator tickets
     *
     * @return
     */
    @GetMapping("/tickets")
    public void operatorViewSelfTickets() {

    }

    //TODO -> entender estoo
    /**
     * View current operator tickets
     *
     * @return
     */
    @PostMapping("/ticket/assign/{ticketId}/{operatorId}")
    public void adminAsignTicket(@PathVariable("ticketId") int ticketId, @PathVariable("operatorId") int operatorId) {

    }

    /**
     * View current operator tickets
     *
     * @return
     */
    @PostMapping("/ticket/{ticketId}/{status}")
    public void adminModifyStatus(@PathVariable("ticketId") int ticketId, @PathVariable("status") TicketStatus ticketStatus) {

    }

    /**
     * View current operator tickets
     *
     * @return
     */
    @DeleteMapping("/ticket/{ticketId}")
    public void adminDeleteTicket(@PathVariable("ticketId") int ticketId) {

    }

}