package com.asaitec.rest.controller;

import com.asaitec.rest.model.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    /**
     * View current operator tickets
     *
     * @return
     */
    @GetMapping("/tickets")
    public void operatorViewSelfTickets() {

    }

    @PostMapping("/ticket/{ticketId}/comment")
    public void operatorTicketComment(@PathVariable("ticketId") int ticketId, @RequestBody List<Comment> comments) {

    }

    /**
     * Mark ticket as completed
     *
     * @param ticketId
     */
    @DeleteMapping("/ticket/{ticketId}")
    public void operatorCloseTicket(@PathVariable("ticketId") int ticketId) {

    }
}