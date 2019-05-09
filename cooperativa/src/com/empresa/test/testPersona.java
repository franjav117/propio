package com.empresa.test;

import java.util.ArrayList;
import java.util.List;

import com.empresa.dao.PersonaDao;
import com.empresa.entities.Beneficiario;
import com.empresa.entities.Cliente;
import com.empresa.entities.Direccion;
import com.empresa.entities.Persona;
import com.empresa.entities.Referencia;
import com.empresa.entities.Telefono;

public class testPersona {
		public static void main(String[]args) {
			Persona persona = new Persona();//cliente
			persona.setNombres("francisco");
			persona.setApellidos("amaya");
			persona.setNumeroDocumento("34-drf-3214-234");
			
			List<Cliente> clilist = new ArrayList<Cliente>();
			Cliente cli = new Cliente();
			cli.setEsMiembro("1");
			cli.setPersona(persona);
			clilist.add(cli);
			
			List<Telefono> telList = new ArrayList<Telefono>();
			Telefono telCli = new Telefono();
			telCli.setTelefono("2344-3242");
			telCli.setPersona(persona);
			telList.add(telCli);
			telCli = new Telefono();
			telCli.setTelefono("7645-3453");
			telCli.setPersona(persona);
			telList.add(telCli);
			
			List<Direccion> dirList = new ArrayList<Direccion>();
			Direccion dir = new Direccion();
			dir.setDireccion("direccion uno");
			dir.setPersona(persona);
			dirList.add(dir);
			
			persona.setDireccions(dirList);
			persona.setTelefonos(telList);
			persona.setClientes(clilist);
			
			//guardando persona
			PersonaDao perDao = new PersonaDao();
			Persona perRest = perDao.insertPersona(persona);
			System.out.println("id persona insertada :"+ perRest.getIdPersona());
			
			//beneficiarios
			List<Beneficiario> benList = new ArrayList<Beneficiario>();
			Beneficiario ben = new Beneficiario();
			Persona perBen = new Persona();
			perBen.setNombres("Ben nombre 1");
			perBen.setApellidos("Ben ape 1");
			perBen.setNumeroDocumento("985679583476");
			Persona respuestaPerBen = perDao.insertPersona(perBen);
			ben.setPersona(respuestaPerBen);
			ben.setCliente(persona.getClientes().get(0));
			ben.setParentesco("padre");
			ben.setEdad(56);
			ben.setPorcentaje(100);
			benList.add(ben);
			
			cli.setBeneficiarios(benList);
			String respuestaBen = perDao.insertBeneficiario(benList);
			System.out.println("Respuesta beneficiarios " + respuestaBen);
			
			//referencias
			List<Referencia> refList = new ArrayList<Referencia>();
			Referencia ref = new Referencia();
			Persona perRef = new Persona();
			perRef.setNombres("Ref nombre 1");
			perRef.setApellidos("ref apellidos 1");
			perRef.setNumeroDocumento("463456437");
			Persona respuestaPerRef = perDao.insertPersona(perRef);
			ref.setPersona(respuestaPerRef);
			ref.setCliente(persona.getClientes().get(0));
			refList.add(ref);
			cli.setReferencias(refList);
			String respuestaRef = perDao.insertReferencias(refList);
			System.out.println("Respuesta referencia " + respuestaRef);
			
		}
	
}
