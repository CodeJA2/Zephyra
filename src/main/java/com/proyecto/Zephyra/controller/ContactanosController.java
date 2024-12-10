 package com.proyecto.Zephyra.controller;

import com.proyecto.Zephyra.model.Contactanos;
import com.proyecto.Zephyra.servicios.CServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactanosController {

	@Autowired
	private CServicio servicio;

	@GetMapping({"/ListasDeContactanos"})
	public String listarContactos(Model modelo) {
		modelo.addAttribute("ListasDeContactanos", servicio.listarTodosLosContactanos());
		return "ListasDeContactanos"; 
	}

	@GetMapping("/ListasDeContactanos/nuevo")
	public String mostrarFormularioDeRegistrtarcontactanos(Model modelo) {
		Contactanos contactanos = new Contactanos();
		modelo.addAttribute("contactanos", contactanos);
		return "CreaContactanos";
	}

	@PostMapping("/ListasDeContactanos")
	public String guardarcontactanos(@ModelAttribute("contactanos") Contactanos contactanos) {
		servicio.guardarContactanos(contactanos);
		return "redirect:/";
	}

	@GetMapping("/ListasDeContactanos/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("contactanos", servicio.obtenerContactanosPorId(id));
		return "EditarContactanos";
	}

	@PostMapping("/ListasDeContactanos/{id}")
	public String actualizarcontactanos(@PathVariable Long id, @ModelAttribute("contactanos") Contactanos contactanos,
			Model modelo) {
		Contactanos contactanosExistente = servicio.obtenerContactanosPorId(id);
		contactanosExistente.setId(id);
                contactanosExistente.setNombreCompleto(contactanos.getNombreCompleto());
                contactanosExistente.setApellidoCompleto(contactanos.getApellidoCompleto());
                contactanosExistente.setEmail(contactanos.getEmail());
                contactanosExistente.setTelefono(contactanos.getTelefono());
                contactanosExistente.setAsunto(contactanos.getAsunto());
                contactanosExistente.setMensaje(contactanos.getMensaje());

		servicio.actualizarContactanos(contactanosExistente);
		return "redirect:/ListasDeContactanos";
	}

	@GetMapping("/ListasDeContactanos/{id}")
	public String eliminarContactanos(@PathVariable Long id) {
		servicio.eliminarContactanos(id);
		return "redirect:/ListasDeContactanos";
	}
        
   
}
