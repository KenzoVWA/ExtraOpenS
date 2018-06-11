package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Movimiento;
@Repository
public interface MovimientoDAO extends JpaRepository<Movimiento, Long> {

	@Query("select f from Movimiento f join fetch f.cuenta c join fetch c.cliente cc where cc.id=?1")
	public List<Movimiento> findporCliente(Long id);
	
	@Query("select f from Movimiento f join fetch f.cuenta c where c.id=?1 and f.createAt=?2")
	public List<Movimiento> findporcuenta(Long id, Date fecha);
	
}
