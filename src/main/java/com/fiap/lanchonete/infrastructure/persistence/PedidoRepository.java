package com.fiap.lanchonete.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;


public interface PedidoRepository  extends JpaRepository<PedidoEntity, Integer>{
}
