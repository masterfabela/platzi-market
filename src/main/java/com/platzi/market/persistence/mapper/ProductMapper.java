package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Product;
import com.platzi.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "nombre",target = "name"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "cantidadStock", target = "stock")
    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);

    @InheritInverseConfiguration()
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
    List<Producto> toProductos(List<Product> products);
}
