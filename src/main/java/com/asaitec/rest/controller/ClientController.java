package com.asaitec.rest.controller;

import com.asaitec.rest.exception.NoOperatorFoundException;
import com.asaitec.rest.exception.NoTicketFoundException;
import com.asaitec.rest.model.*;
import com.asaitec.rest.repository.OperatorRepository;
import com.asaitec.rest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final OperatorRepository operatorRepository;
    private final TicketRepository ticketRepository;

    public ClientController(OperatorRepository operatorRepository, TicketRepository ticketRepository) {
        this.operatorRepository = operatorRepository;
        this.ticketRepository = ticketRepository;
    }

    /**
     * List all operators filtered by it's status.
     *
     * @return List<Operator> that matches current criteria. Can be empty.
     */
    @GetMapping("/list/operators/{operatorStatus}")
    public List<Operator> initial(@PathVariable("operatorStatus") OperatorStatus operatorStatus) {
        return this.operatorRepository.getOperatorsByStatus(operatorStatus);
    }

    /**
     * Creates a ticket. Should be created under it's user, but simplified.
     *
     * @return Created Ticket.
     */
    @PutMapping("/ticket/create")
    public Ticket clientCreateTicket(@RequestBody Ticket ticket) throws NoOperatorFoundException {
        return this.ticketRepository.putTicket(ticket);
    }

    /**
     * Assigns a ticket to an existing operator. Should be filtered to only self-created tickets by client, but simplified.
     *
     * @param operator Operator as body (using id), ticketId as param (valid ticket ID)
     * @return
     */
    @PostMapping("/ticket/{ticketId}/assign")
    public Ticket initial(@RequestBody Operator operator, @PathVariable("ticketId") long ticketId) throws NoTicketFoundException, NoOperatorFoundException {
        return this.ticketRepository.assignTicket(ticketId, operator.getId());
    }

    /**
     * Retrieve ticket comments
     *
     * @param ticketId valid ticket id as long
     */
    @GetMapping("/ticket/{ticketId}/comments")
    public List<Comment> clientReadTicketComments(@PathVariable("ticketId") long ticketId) throws NoTicketFoundException {
        Optional<Ticket> ticketOptional = this.ticketRepository.findOneById(ticketId);
        if (ticketOptional.isEmpty()) {
            throw new NoTicketFoundException();
        }
        return ticketOptional.get().getComments();
    }

    /**
     * Add comments to a ticket, and return ticket.
     * @param ticketId The id of the ticket, must exists.
     * @param comments The list of comments to add.
     * @return Ticket.
     * @throws NoTicketFoundException If there is no ticket.
     */
    @PostMapping("/ticket/{ticketId}/comments")
    public Ticket clientAddTicketComments(@PathVariable("ticketId") long ticketId, @RequestBody List<Comment> comments) throws NoTicketFoundException {
        return this.ticketRepository.addComments(ticketId, comments);
    }

    /**
     * Close given ticket is present.
     * @param ticketId TicketId.
     * @return Closed ticket.
     * @throws NoTicketFoundException If there is no ticket.
     */
    @DeleteMapping("/ticket/{ticketId}")
    public Ticket clientCloseTicket(@PathVariable("ticketId") int ticketId) throws NoTicketFoundException {
        return this.ticketRepository.modifyTicketStatus(ticketId, TicketStatus.CLOSED);
    }
}