package com.proyecto.Zephyra.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PagoController {
    @Autowired
    private APIContext apiContext;

    @PostMapping("/pago/iniciar")
    public String iniciarPago(@RequestParam Double total, Model model) {
        try {
            // Crear un pago con PayPal
            Payment payment = new Payment();
            payment.setIntent("sale")
                .setPayer(new Payer().setPaymentMethod("paypal"))
                .setTransactions(createTransactions(total))
                .setRedirectUrls(new RedirectUrls()
                    .setCancelUrl("http://localhost:8080/pago/cancelar")
                    .setReturnUrl("http://localhost:8080/pago/exito"));

            // Crear la URL de autorización de PayPal
            Payment createdPayment = payment.create(apiContext);

            // Obtener la URL de aprobación para redirigir al usuario
            for (Links link : createdPayment.getLinks()) {
                if ("approval_url".equals(link.getRel())) {
                    model.addAttribute("paypalUrl", link.getHref());
                    return "pagoVista"; // Retorna la vista de Thymeleaf que contiene el botón de redirección
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return "error";
        }
        return "error";
    }

    private List<Transaction> createTransactions(Double total) {
        Transaction transaction = new Transaction();
        transaction.setAmount(new Amount().setTotal(String.valueOf(total)).setCurrency("USD"));
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        return transactions;
    }


     @GetMapping("/pagoExitoso")
    public String pagoExitoso() {
        return "pagoExitoso";
    }
}
