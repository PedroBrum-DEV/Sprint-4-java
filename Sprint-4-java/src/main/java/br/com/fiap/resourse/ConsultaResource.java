package br.com.fiap.resourse;

import br.com.fiap.bo.ConsultaBO;
import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.to.ConsultaTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/Consulta")
public class ConsultaResource {

    private ConsultaDAO consultaDAO;

    public ConsultaResource() throws SQLException, ClassNotFoundException {
        this.consultaDAO = new ConsultaDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarConsultas() throws SQLException {
        List<ConsultaTO> consultas = consultaDAO.listarConsultas();
        if (consultas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma consulta encontrada.")
                    .build(); // 404
        }
        return Response.ok(consultas).build(); // 200
    }

    @GET
    @Path("/paciente/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorPaciente(@PathParam("cpf") String cpf) throws SQLException {
        List<ConsultaTO> consultas = consultaDAO.listarConsultasPaciente(cpf);
        if (consultas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma consulta encontrada para o paciente com CPF " + cpf)
                    .build();
        }
        return Response.ok(consultas).build();
    }

    @GET
    @Path("/medico/{crm}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorMedico(@PathParam("crm") String crm) throws SQLException {
        List<ConsultaTO> consultas = consultaDAO.listarConsultasMedico(crm);
        if (consultas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma consulta encontrada para o médico com CRM " + crm)
                    .build();
        }
        return Response.ok(consultas).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarConsulta(ConsultaTO consulta) throws SQLException {
        try {
            // Reutiliza regras de negócio da BO
            ConsultaBO bo = new ConsultaBO();
            if (!bo.validarDataHora(consulta) || !bo.validarStatus(consulta)) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Data/hora inválida ou status incorreto (use: Agendada, Concluída ou Cancelada).")
                        .build();
            }

            consultaDAO.cadastrarConsulta(consulta);
            return Response.status(Response.Status.CREATED)
                    .entity("Consulta cadastrada com sucesso!")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar consulta: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarConsulta(@PathParam("id") int id, ConsultaTO consulta) throws SQLException {
        consulta.setId(id);
        boolean atualizado = consultaDAO.atualizarConsulta(consulta);
        if (atualizado) {
            return Response.ok("Consulta atualizada com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Consulta ID " + id + " não encontrada.")
                    .build();
        }
    }

    @PUT
    @Path("/cancelar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelarConsulta(@PathParam("id") int id) throws SQLException {
        boolean cancelado = consultaDAO.cancelarConsulta(id);
        if (cancelado) {
            return Response.ok("Consulta ID " + id + " cancelada com sucesso.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Consulta ID " + id + " não encontrada.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarConsulta(@PathParam("id") int id) throws SQLException {
        boolean deletado = consultaDAO.deletarConsulta(id);
        if (deletado) {
            return Response.ok("Consulta removida com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Consulta ID " + id + " não encontrada.")
                    .build();
        }
    }
}
