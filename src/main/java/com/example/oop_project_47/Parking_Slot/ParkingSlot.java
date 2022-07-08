package com.example.oop_project_47.Parking_Slot;

import com.example.oop_project_47.Model.NamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Parking_Slots")
public class ParkingSlot extends NamedEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Slot_Type")
    @NotEmpty
    private String slotType;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }
}
