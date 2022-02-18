package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Compra;
import com.platzi.market.persistence.entity.ComprasProducto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ComprasProductoCrudRepository extends CrudRepository<ComprasProducto, Integer> {
    Optional<List<ComprasProducto>> findByCompra(Compra compra);
}
