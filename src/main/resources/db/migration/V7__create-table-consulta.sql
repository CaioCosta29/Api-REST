create table consulta (

    id bigint not null auto_increment,
    id_medico bigint not null,
    id_paciente bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_medico_id foreign key(id_medico) references medicos(id),
    constraint fk_consultas_paciente_id foreign key(id_paciente) references paciente(id)

);