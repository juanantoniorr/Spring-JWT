package com.bolsadeideas.springboot.app.view.csv;

import java.io.FileWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Component("listar.csv") //nombre de la vista html se puede agregar el csv si en el properties estan el content negotiation

//es del paquete spring porque no hay implementacion spring de csv {
public class ClienteCsvView extends AbstractView {

	
	
	public ClienteCsvView() {
		setContentType("text/csv");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		response.setHeader("Content-Disposition", "attachment; filename=\"clientes.csv\"");
		response.setContentType(getContentType());
		Page <Cliente> clientes = (Page<Cliente>) model.get("clientes");
		ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
		String [] header = {"id","nombre","apellido","email","createAt"}; //igual a como estan en el entity
		beanWriter.writeHeader(header);
		for(Cliente cliente:clientes) {
			beanWriter.write(cliente, header);
			
		}
		beanWriter.close();
	}

	@Override
	protected boolean generatesDownloadContent() {
		
		return true;
	}
	

}
