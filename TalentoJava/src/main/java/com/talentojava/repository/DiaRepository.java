package com.talentojava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.talentojava.dia.Dia;

public interface DiaRepository extends JpaRepository<Dia, Long> {
	@Query("SELECT d FROM Dia d WHERE d.hoje = true")
	public List<Dia> findHoje();
}
