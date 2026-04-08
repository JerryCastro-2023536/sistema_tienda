package com.jerrycastro.tienda.Repository;

import com.jerrycastro.tienda.Entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepostory extends JpaRepository<Ventas, Integer> {
}
