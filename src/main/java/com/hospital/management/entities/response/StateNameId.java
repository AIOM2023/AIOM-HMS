package com.hospital.management.entities.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class StateNameId {


    private  Long stateId;

    private  String stateName;

}
