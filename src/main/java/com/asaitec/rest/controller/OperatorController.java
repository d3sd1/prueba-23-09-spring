package com.asaitec.rest.controller;

import com.asaitec.rest.exception.NoTicketFoundException;
import com.asaitec.rest.model.Comment;
import com.asaitec.rest.model.Ticket;
import com.asaitec.rest.model.TicketStatus;
import com.asaitec.rest.repository.OperatorRepository;
import com.asaitec.rest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    private final OperatorRepository operatorRepository;
    private final TicketRepository ticketRepository;

    public OperatorController(OperatorRepository operatorRepository, TicketRepository ticketRepository) {
        this.operatorRepository = operatorRepository;
        this.ticketRepository = ticketRepository;
    }

    /**
     * View current operator tickets
     *
     * @return List<Ticket> for current operator session.
     */
    @GetMapping("/tickets")
    public List<Ticket> operatorViewSelfTickets(@RequestHeader("user_id") long operatorId) {
        return this.ticketRepository.findByAssignedOperator(operatorId);
    }

    /**
     * Add comments to ticket.
     *
     * @param ticketId TicketId
     * @param comments Comments to add.
     * @return Ticket with comments.
     * @throws NoTicketFoundException If no ticket found.
     */
    @PostMapping("/ticket/{ticketId}/comment")
    public Ticket operatorTicketComment(@PathVariable("ticketId") long ticketId, @RequestBody List<Comment> comments) throws NoTicketFoundException {
        return this.ticketRepository.addComments(ticketId, comments);
    }

    /**
     * Closes a ticket (mark as completed).
     *
     * @param ticketId TicketId long.
     * @return Closed ticket.
     * @throws NoTicketFoundException If no ticket found.
     */
    @DeleteMapping("/ticket/{ticketId}")
    public Ticket operatorCloseTicket(@PathVariable("ticketId") long ticketId) throws NoTicketFoundException {
        return this.ticketRepository.modifyTicketStatus(ticketId, TicketStatus.CLOSED);
    }
}