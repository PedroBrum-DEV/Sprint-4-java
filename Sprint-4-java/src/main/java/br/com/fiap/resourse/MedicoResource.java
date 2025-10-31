package br.com.fiap.resourse;

import br.com.fiap.bo.MedicoBO;
import br.com.fiap.to.MedicoTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/Medico")
public class MedicoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMedicos() throws SQLException, ClassNotFoundException {
        MedicoBO bo = new MedicoBO();
        List<MedicoTO> medicos = bo.listarMedicos();

        if (medicos != null && !medicos.isEmpty()) {
            return Response.ok(medicos).build(); // 200 OK
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum médico encontrado")
                    .build(); // 404 NOT FOUND
        }
    }

    @GET
    @Path("/{crm}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorCRM(@PathParam("crm") String crm) throws SQLException, ClassNotFoundException {
        MedicoBO bo = new MedicoBO();
        MedicoTO medico = bo.buscarPorCRM(crm);

        if (medico != null) {
            return Response.ok(medico).build(); // 200 OK
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Médico não encontrado para o CRM: " + crm)
                    .build(); // 404 NOT FOUND
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarMedico(MedicoTO medico) throws SQLException, ClassNotFoundException {
        MedicoBO bo = new MedicoBO();
        bo.cadastrarMedico(medico);
        return Response.status(Response.Status.CREATED)
                .entity(medico)
                .build(); // 201 CREATED
    }

    @PUT
    @Path("/{crm}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarMedico(@PathParam("crm") String crm, MedicoTO medico) throws SQLException, ClassNotFoundException {
        MedicoBO bo = new MedicoBO();
        medico.setCrm(crm); // garante que o CRM da URL seja usado
        boolean existe = bo.buscarPorCRM(crm) != null;

        if (!existe) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Médico não encontrado para atualização")
                    .build(); // 404 NOT FOUND
        }

        bo.atualizarMedico(medico);
        return Response.ok(medico).build(); // 200 OK
    }

    @DELETE
    @Path("/{crm}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarMedico(@PathParam("crm") String crm) throws SQLException, ClassNotFoundException {
        MedicoBO bo = new MedicoBO();
        boolean existe = bo.buscarPorCRM(crm) != null;

        if (!existe) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Médico não encontrado para exclusão")
                    .build(); // 404 NOT FOUND
        }

        bo.deletarMedico(crm);
        return Response.ok("Médico deletado com sucesso").build(); // 200 OK
    }
}
