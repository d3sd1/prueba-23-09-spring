package com.asaitec.rest.controller;

import com.asaitec.rest.model.Comment;
import com.asaitec.rest.model.Operator;
import com.asaitec.rest.model.OperatorStatus;
import com.asaitec.rest.model.Ticket;
import com.asaitec.rest.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private OperatorRepository operatorRepository;

    public ClientController(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    /**
     * List all operators filtered by it's status.
     * @return List<Operator> that matches current criteria. Can be empty.
     */
    @GetMapping("/list/operators/{operatorStatus}")
    public List<Operator> initial(@PathVariable("operatorStatus") OperatorStatus operatorStatus) {
        return this.operatorRepository.getOperatorsByStatus(operatorStatus);
    }

    /**
     *
     * @return
     */
    @PutMapping("/ticket/create")
    public String clientCreateTicket(Ticket ticket) {
        return "Welcome to asaitec!";
    }

    /**
     * Assigns a ticket to an existing operator.
     * @param operator
     * @return
     */
    @PostMapping("/ticket/{ticketId}/assign")
    public String initial(@RequestBody Operator operator, @PathVariable("ticketId") int ticketId) {
        return "Welcome to asaitec!";
    }

    @GetMapping("/ticket/{ticketId}/comments")
    public void clientReadTicketComments(@PathVariable("ticketId") int ticketId) {

    }

    @PostMapping("/ticket/{ticketId}/comments")
    public void clientAddTicketComments(@PathVariable("ticketId") int ticketId, @RequestBody List<Comment> comments) {

    }

    /**
     *
     * @param ticketId
     */
    @DeleteMapping("/ticket/{ticketId}")
    public void clientCloseTicket(@PathVariable("ticketId") int ticketId) {

    }
}