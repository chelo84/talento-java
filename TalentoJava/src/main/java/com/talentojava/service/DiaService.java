package com.talentojava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentojava.dia.Dia;
import com.talentojava.repository.DiaRepository;

@Service
public class DiaService {
	@Autowired
	private DiaRepository dias;
	
	public Dia findHoje() {
		List<Dia> hj = dias.findHoje();
		Dia hoje;
		if(!hj.isEmpty()) {
			hoje = hj.get(hj.size()-1);
		} else {
			hoje = new Dia();
			hoje.setHoje(true);
		}
		
		return hoje;
	}
	
	public List<Dia> findTodosDias() {
		return dias.findAll();
	}
	
	public Dia save(Dia dia) {
		return dias.saveAndFlush(dia);
	}
}
