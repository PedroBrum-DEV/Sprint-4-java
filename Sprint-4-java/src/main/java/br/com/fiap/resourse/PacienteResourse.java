package br.com.fiap.resourse;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.to.PacienteTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/Paciente")
public class PacienteResourse {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException, ClassNotFoundException {
        ArrayList<PacienteTO> resultado = new PacienteBO().findAll();
        Response.ResponseBuilder response = null;
        if(resultado != null){
            response= Response.ok();
        } else {
            response= response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }
}
