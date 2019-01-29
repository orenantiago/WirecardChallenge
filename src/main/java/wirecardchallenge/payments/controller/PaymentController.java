package wirecardchallenge.payments.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wirecardchallenge.Views;
import wirecardchallenge.payments.Service.PaymentService;
import wirecardchallenge.payments.model.Payment;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService service;

    @JsonView(value = Views.Public.class)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/payments", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody Payment processPayment(@RequestBody Payment payment) throws NotFoundException {
        return service.createPayment(payment);
    }

    @JsonView(value = Views.Public.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/payments", method = RequestMethod.GET,
            produces = "application/json")
    public List<Payment> getAllPayments(){
        return service.findAll();
    }

    @JsonView(value = Views.Detail.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET,
            produces = "application/json")
    public Payment getPayment(@PathVariable Integer id) throws NotFoundException {
        return service.findById(id);
    }
}
