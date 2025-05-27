package org.example.dao;

import org.example.domain.Moto;

import java.sql.SQLException;

public interface MotoDAO {

    Moto cadastrarMoto(Moto moto) throws SQLException;
}
