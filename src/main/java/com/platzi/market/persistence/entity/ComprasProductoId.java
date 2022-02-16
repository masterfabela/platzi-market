package com.platzi.market.persistence.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ComprasProductoId implements Serializable {
    private static final long serialVersionUID = -7635029233068743577L;
    @Column(name = "id_compra", nullable = false)
    private Integer idCompra;
    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra, idProducto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ComprasProductoId entity = (ComprasProductoId) o;
        return Objects.equals(this.idCompra, entity.idCompra) &&
                Objects.equals(this.idProducto, entity.idProducto);
    }
}