package com.empresa.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.empresa.dao.PersonaDao;
import com.empresa.entities.Beneficiario;
import com.empresa.entities.Cliente;
import com.empresa.entities.Direccion;
import com.empresa.entities.Persona;
import com.empresa.entities.PersonaGenerica;
import com.empresa.entities.Referencia;
import com.empresa.entities.Telefono;

@ManagedBean
@ViewScoped
public class RegistroMb {
    private Persona persona;
	private List<Cliente> cliList;
	private Cliente cliente;
	private List<Direccion> listDirCliente;
	private List<Telefono> listTelCliente;
	private List<Referencia> listRefCliente;
	private List<PersonaGenerica> referencias;
	private List<PersonaGenerica> beneficiarios;
	private List<Beneficiario> listBenCliente;
	private PersonaDao perDao;
	
	@PostConstruct
	public void init() {
		
	     persona = new Persona();
		 cliList = new ArrayList<Cliente>();
		 cliente = new Cliente();		
		 listDirCliente = new ArrayList<Direccion>();
		 listTelCliente = new ArrayList<Telefono>() ;
		 
		 referencias = new ArrayList<PersonaGenerica>() ;
		 beneficiarios = new ArrayList<PersonaGenerica>() ;
		 
		 listRefCliente = new ArrayList<Referencia>();
		 listBenCliente = new ArrayList<Beneficiario>();
		 perDao = new PersonaDao();
	}
	
	 public void insertCliente() {
		 cliente.setPersona(persona);
		 cliList.add(cliente);
		 persona.setClientes(cliList);
		 for(Direccion dir:listDirCliente) {
			 dir.setPersona(persona);
		 }
		 persona.setDireccions(listDirCliente);
		 for(Telefono tel:listTelCliente) {
			 tel.setPersona(persona);
		 }
		 persona.setTelefonos(listTelCliente);
		 for(PersonaGenerica per: beneficiarios) {
			 Beneficiario ben = new Beneficiario(); 
			 ben.setCliente(cliente);
			 ben.setEdad(per.getEdad());
			 ben.setParentesco(per.getParentesco());
			 Persona perBen = new Persona();
			 perBen.setNombres(per.getNombre());
			 perBen.setApellidos(per.getApellido());
			 perBen.setNumeroDocumento(per.getNumeroDocumento());
			 Persona perRespuesta = perDao.insertPersona(perBen);
			 ben.setPersona(perRespuesta);
			 ben.setPorcentaje(per.getPorcentaje());
			 listBenCliente.add(ben);
		 }
		 persona.setBeneficiarios(listBenCliente);
		 for(PersonaGenerica perGen: referencias) {
			 Referencia ref = new Referencia();
			 ref.setCliente(cliente);
			 Persona perBen = new Persona();
			 perBen.setNombres(perGen.getNombre());
			 perBen.setApellidos(perGen.getApellido());
			 perBen.setNumeroDocumento(perGen.getNumeroDocumento());
			 Persona personaGeRespuesta = perDao.insertPersona(perBen);
			 ref.setPersona(personaGeRespuesta);
			 ref.setTiempoconocerse(perGen.getTiempoconocerse());
			 listRefCliente.add(ref);
			 
		 }
		 persona.setReferencias(listRefCliente);
		 Persona clienteGuardado = perDao.insertPersona(persona);
		 FacesMessage msg = new FacesMessage("persona guardada"+clienteGuardado.getIdPersona());
		 FacesContext.getCurrentInstance().addMessage(null, msg);
	 }
	 
	public void addDireccion() {
		listDirCliente.add(new Direccion());
	}
	
	public void addTelefono() {
		listTelCliente.add(new Telefono());
	}
	public void addReferencia() {
		referencias.add(new PersonaGenerica());
	}
	public void addBeneficiario() {
		beneficiarios.add(new PersonaGenerica());
	}
	
	public String iraCuentas() {
		return "cuentas.xhtml";
	}
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public List<Cliente> getCliLit() {
		return cliList;
	}
	public void setCliLit(List<Cliente> cliLit) {
		this.cliList = cliLit;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Direccion> getListDirCliente() {
		return listDirCliente;
	}
	public void setListDirCliente(List<Direccion> listDirCliente) {
		this.listDirCliente = listDirCliente;
	}
	public List<Telefono> getListTelCliente() {
		return listTelCliente;
	}
	public void setListTelCliente(List<Telefono> listTelCliente) {
		this.listTelCliente = listTelCliente;
	}
	public List<Referencia> getListRefCliente() {
		return listRefCliente;
	}
	public void setListRefCliente(List<Referencia> listRefCliente) {
		this.listRefCliente = listRefCliente;
	}
	public List<Beneficiario> getListBenCliente() {
		return listBenCliente;
	}
	public void setListBenCliente(List<Beneficiario> listBenCliente) {
		this.listBenCliente = listBenCliente;
	}

	public List<PersonaGenerica> getReferencias() {
		return referencias;
	}

	public void setReferencias(List<PersonaGenerica> referencias) {
		this.referencias = referencias;
	}

	public List<PersonaGenerica> getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(List<PersonaGenerica> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
	
	
	
	
}
