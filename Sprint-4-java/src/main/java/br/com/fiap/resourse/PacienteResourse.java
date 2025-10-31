package br.com.fiap.resourse;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.to.PacienteTO;
import jakarta.ws.rs.*;
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

        if (resultado != null && !resultado.isEmpty()) {
            return Response.ok(resultado).build(); // 200 OK
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum paciente encontrado")
                    .build(); // 404 NOT FOUND
        }
    }

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCpf(@PathParam("cpf") String cpf) throws SQLException, ClassNotFoundException {
        PacienteTO paciente = new PacienteBO().findAll().stream()
                .filter(p -> p.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (paciente != null) {
            return Response.ok(paciente).build(); // 200 OK
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Paciente não encontrado para o CPF: " + cpf)
                    .build(); // 404 NOT FOUND
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(PacienteTO paciente) throws SQLException, ClassNotFoundException {
        new PacienteBO().cadastrarPaciente(paciente);
        return Response.status(Response.Status.CREATED)
                .entity(paciente)
                .build(); // 201 CREATED
    }

    @PUT
    @Path("/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("cpf") String cpf, PacienteTO paciente) throws SQLException, ClassNotFoundException {
        PacienteBO bo = new PacienteBO();
        paciente.setCpf(cpf);

        boolean existe = bo.findAll().stream().anyMatch(p -> p.getCpf().equals(cpf));

        if (!existe) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Paciente não encontrado para atualização")
                    .build(); // 404 NOT FOUND
        }

        bo.cadastrarPaciente(paciente); // ideal: método atualizarPaciente
        return Response.ok(paciente).build(); // 200 OK
    }

    @DELETE
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("cpf") String cpf) throws SQLException, ClassNotFoundException {
        PacienteBO bo = new PacienteBO();
        boolean existe = bo.findAll().stream().anyMatch(p -> p.getCpf().equals(cpf));

        if (!existe) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Paciente não encontrado para exclusão")
                    .build(); // 404 NOT FOUND
        }

        bo.findAll().removeIf(p -> p.getCpf().equals(cpf)); // ideal: método deletarPaciente
        return Response.ok("Paciente deletado com sucesso").build(); // 200 OK
    }
}
