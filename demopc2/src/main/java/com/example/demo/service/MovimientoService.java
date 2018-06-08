package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CuentaDAO;
import com.example.demo.dao.MovimientoDAO;
import com.example.demo.entity.Cuenta;
import com.example.demo.entity.Movimiento;
import com.example.demo.iservice.IMovimientoService;
@Service
public class MovimientoService implements IMovimientoService {

	@Autowired
	public MovimientoDAO dao;
	
	@Autowired
	public CuentaDAO dao2;
	
	@Override
	@Transactional
	public void save(Movimiento movimiento) {
		// TODO Auto-generated method stub

		
		if(movimiento.getMonto()<movimiento.getCuenta().getSaldobase()) {

		actualizarcta(movimiento);
		
		dao.save(movimiento);
		
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Movimiento> findbycuenta(Long id) {
		// TODO Auto-generated method stub
		return dao.findporCliente(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Movimiento> findall() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void actualizarcta(Movimiento movimiento) {
		// TODO Auto-generated method stub
		int numero=movimiento.getMonto();
		Long saldo=movimiento.getCuenta().getSaldobase();
		Long recalculo;
		if(movimiento.getTipo().equalsIgnoreCase("retiro")) {
			recalculo=saldo-numero;
			Cuenta cuenta=movimiento.getCuenta();
			cuenta.setSaldobase(recalculo);
			dao2.save(cuenta);
		}else if(movimiento.getTipo().equalsIgnoreCase("deposito")){
			recalculo=saldo+numero;
			Cuenta cuenta=movimiento.getCuenta();
			cuenta.setSaldobase(recalculo);
			dao2.save(cuenta);
		}
	}

}
