package com.example.examen.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.example.examen.entity.DetallePedido;
import com.example.examen.entity.Pedido;
import com.example.examen.entity.Plato;
import com.example.examen.entity.Usuario;
import com.example.examen.service.DetallePedidoService;
import com.example.examen.service.PedidoService;
import com.example.examen.service.PlatoService;
import com.example.examen.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuario;
	
	@Autowired
	private PedidoService pedidoS;
	

	@Autowired
	private DetallePedidoService detalleS;
	
	@Autowired
	private PlatoService  platoS;
	
	@Autowired
	private PasswordEncoder passwordEncoder ;
	
	@GetMapping
	public String inicio (Model model) {
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			 /*lista = pedido.findById();*/
		} catch (Exception e) {;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("pedidos", lista);
		return "usuario/inicio";
		
	}
	
	
	@GetMapping("/register")
	public String registrarUsuario (Model model) {
		Usuario usuario = new Usuario(); 
		
		model.addAttribute("usuario", usuario);
		return "usuario/register";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("usuario") Usuario _usuario, 
			Model model, SessionStatus status) {
		
		try {
			// Verificar que el username ya exista.
			Optional<Usuario> optional 
				= usuario.findByUsername( _usuario.getUsername());
			if(optional.isPresent()) {
				model.addAttribute("dangerRegister"
						, "ERROR - El username " 
							+  _usuario.getUsername() 
							+ " ya existe ");
				return "/usuario/nuevo";
			} else {
				 _usuario.setPassword(passwordEncoder
						.encode(  _usuario.getPassword() ));
				 _usuario.addAuthority("ROLE_CLIENTE");
				usuario.save(_usuario);
				status.setComplete();
			}
		} catch (Exception e) {
			model.addAttribute("dangerRegister"
					, "ERROR - El username " 
						+  _usuario.getUsername() 
						+ " ya existe ");
			return "usuario/register";
		}
		return "/login";
	}
	
	@GetMapping("/pedido/info/{id}")
	public String info (@PathVariable long id,Model model,SessionStatus session) {
		try {
			Optional<Pedido> pedidoOptional = pedidoS.findById(id);
			if (pedidoOptional.isPresent()) {
				
				List<DetallePedido> listaDetalles = pedidoOptional.get().getDetalles();
				model.addAttribute("detallesPedido", listaDetalles);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "usuario/info";
	}
	
	@GetMapping("pedido/nuevo/{id}")
	public String nuevo(@PathVariable int id,Model model, SessionStatus session) {
		
		/*DetallePedido detallePedido = new DetallePedido();*/
		try {
			Optional<Plato> platoO = platoS.findById(id);
			if (platoO.isPresent()) {
			Pedido pedido = new Pedido();
			pedido.setEstado("no confirmado");
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			
			DetallePedido _detalle = new DetallePedido();
			_detalle.setEstado("no servido");
			
			_detalle.setPlato(platoO.get());
			
				Optional<Usuario> optional = usuario.findByUsername(username);
				if (optional.isPresent()) {
					pedido.setUsuario(optional.get());
					
				} else {
					return "access-denied";
				}
			
				
					pedidoS.save(pedido);
					Optional<Pedido> ultimoPedido = pedidoS.fetchUltimopedido();
					if(ultimoPedido.isPresent()) {
					_detalle.setPedido(ultimoPedido.get());	
					detalleS.save(_detalle);
					session.setComplete();
					
					List<Pedido> pedidosByUsuario = optional.get().getPedido();
					model.addAttribute("pedidos", pedidosByUsuario);
					}else {
						Exception ex = new 	Exception() ;
						throw  ex;
					}
	
			return "usuario/inicio";
				
			}else {
				List<Plato> lista = new ArrayList<Plato>();
				
				lista = platoS.findAll();				
				model.addAttribute("dangerSave", "Error");
				model.addAttribute("platos",lista);
				return "index";

			}
					
		} catch (Exception e1) {
			List<Plato> lista = new ArrayList<Plato>();
			try {
				lista = platoS.findAll();
			} catch (Exception e) {
				model.addAttribute("dangerSave", "Error");
			}
			
		model.addAttribute("platos",lista);
		return "index";
		}
		
		

	}
	
	
	@GetMapping("pedido/confirmar/{id}")
	public String confirmar(@PathVariable long id,Model model, SessionStatus session) {
		try {
		Optional<Pedido> pedidoOptional = pedidoS.findById(id); 
		if (pedidoOptional.isPresent() ) {
			
			if(pedidoOptional.get().getEstado().equalsIgnoreCase("no confirmado")) {
				try {
					pedidoOptional.get().setEstado("confirmado");
					pedidoS.save(pedidoOptional.get());
					session.setComplete();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				
				
			
					List<Pedido> listaPedido =pedidoS.findAll();
					model.addAttribute("pedidos", listaPedido);

			}
			
			
		}

		return "usuario/inicio";
		
		} catch (Exception e) {
			List<Plato> listaPlato=new ArrayList<Plato>();
			try {
				listaPlato = platoS.findAll();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
			model.addAttribute("platos",listaPlato);
			return "index";
		}

	}
	
	
	@GetMapping("pedido/delete/{id}")
	public String descartar(@PathVariable long id,Model model, SessionStatus session) {
		try {
		Optional<Pedido> pedidoOptional = pedidoS.findById(id); 
		if (pedidoOptional.isPresent() ) {
			
			if(pedidoOptional.get().getEstado().equalsIgnoreCase("no confirmado")) {
				try {
					detalleS.deleteByPedido(id);
					pedidoS.deleteById(id); 
					session.setComplete();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}

			}
			
			
		}
		List<Pedido> listaPedido =pedidoS.findAll();
		model.addAttribute("pedidos", listaPedido);

		return "usuario/inicio";
		
		} catch (Exception e) {
			List<Plato> listaPlato=new ArrayList<Plato>();
			try {
				listaPlato = platoS.findAll();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.addAttribute("platos",listaPlato);
			return "index";
		}

	}
	

	
}
