package com.platzi.market.persistence;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.domain.repository.PurchaseRepository;
import com.platzi.market.persistence.crud.CompraCrudRepository;
import com.platzi.market.persistence.crud.ComprasProductoCrudRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Compra;
import com.platzi.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ComprasProductoCrudRepository comprasProductoCrudRepository;
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId).map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(comprasProducto -> {
            comprasProducto.setCompra(compra);
            productoCrudRepository.findById(comprasProducto.getId().getIdProducto())
                    .ifPresent(comprasProducto::setProducto);
        });
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }

    @Override
    public boolean delete(int purchaseId) {
        return compraCrudRepository.findById(purchaseId).map(compra -> {
            comprasProductoCrudRepository.findByCompra(compra)
                    .ifPresent(comprasProductos ->
                            comprasProductoCrudRepository.deleteAll(comprasProductos)
                    );
            compraCrudRepository.delete(compra);
            return true;
        }).orElse(false);
    }
}
