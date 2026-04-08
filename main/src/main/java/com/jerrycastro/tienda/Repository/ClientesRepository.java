package com.jerrycastro.tienda.Repository;

import com.jerrycastro.tienda.Entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository <Clientes, Integer> {
}
