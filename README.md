🏥 Sistema de Gestão de Clínica Médica

Integrantes
Nome	RM	Turma
Pedro Henrique Brum	RM: 558275	1TDSPG
Luis Felipe Flosi	RM: 558302	1TDSPG
Arthur Brito	RM: 556688	1TDSPG

📘 Descrição do Projeto

O sistema tem como objetivo oferecer aos pacientes uma plataforma simples e acessível, permitindo visualizar informações de suas consultas, incluindo data, especialidade, status e médico responsável, além de poder cancelar consultas diretamente pelo site.

O projeto foi desenvolvido em Java com Quarkus, seguindo o padrão MVC (Model-View-Controller), com separação das camadas TO, DAO, BO e Resource, garantindo organização e fácil manutenção.

Além disso, o sistema traz consigo as informações mais buscadas pelos pacientes, promovendo agilidade e transparência no atendimento médico.

🎯 Objetivo

Simplificar o acesso dos pacientes às informações médicas, eliminando barreiras tecnológicas e tornando o acompanhamento de consultas mais rápido e intuitivo.

💡 Propósito

Proporcionar uma experiência digital eficiente e acessível, com foco na usabilidade, autonomia do paciente e eficiência na gestão clínica.

Estrutura de Pacotes
src
 └── main
     └── java
         └── br.com.fiap
             ├── bo
             │    ├── ConsultaBO.java
             │    ├── MedicoBO.java
             │    └── PacienteBO.java
             ├── dao
             │    ├── ConnectionFactory.java
             │    ├── ConsultaDAO.java
             │    ├── MedicoDAO.java
             │    └── PacienteDAO.java
             ├── resource
             │    ├── ConsultaResource.java
             │    ├── MedicoResource.java
             │    └── PacienteResource.java
             ├── to
             │    ├── ConsultaTO.java
             │    ├── MedicoTO.java
             │    └── PacienteTO.java
             └── utilidades
                  ├── Validacoes.java
                  └── GreetingResource.java

🧠 Classes Principais
PacienteTO

Atributos: nome, cpf, dataNascimento, senha

Métodos: Getters e Setters

Relação: Indica → MedicoTO

MedicoTO

Atributos: idMedico, nome, especialidade

Métodos: Getters e Setters

Relação: Indica → ConsultaTO

ConsultaTO

Atributos: id, cpfPaciente, crmMedico, dataHora, status

Métodos: Getters e Setters

Relação: Consulta → PacienteTO
