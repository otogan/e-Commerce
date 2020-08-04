package com.ecommerce.web.data.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ORDER_ITEMS")
@AssociationOverrides({
        @AssociationOverride(name = "id.order", joinColumns = @JoinColumn(name = "ORDER_ID")),
        @AssociationOverride(name = "id.product", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
})
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    @Column(name = "UNIT_PRICE", nullable = false, columnDefinition = "decimal(4,2)")
    private double unitPrice;

    public OrderItem() {
    }

    public OrderItem(OrderItemId id, int quantity, double unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    @Transient
    public Order getOrder() {
        return getId().getOrder();
    }

    public void setOrder(Order order) {
        getId().setOrder(order);
    }

    @Transient
    public Product getProduct() {
        return getId().getProduct();
    }

    public void setProduct(Product product) {
        getId().setProduct(product);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", product=" + id.getProduct() +
                '}';
    }
}
