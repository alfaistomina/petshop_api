package org.example.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class OrderPet {

    @SerializedName("id")
    private Integer id;

    @SerializedName("petId")
    private Integer petId;

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("shipDate")
    private String shipDate;

    @SerializedName("status")
    private Status status;

    @SerializedName("complete")
    private Boolean complete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate='" + shipDate + '\'' +
                ", status=" + status +
                ", complete=" + complete +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderPet order = (OrderPet) obj;
        return Objects.equals(id, order.id) && Objects.equals(petId, order.petId) && Objects.equals(quantity, order.quantity) && Objects.equals(shipDate, order.shipDate) && Objects.equals(status, order.status) && complete == order.complete;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petId, quantity, shipDate, status, complete);
    }
}
