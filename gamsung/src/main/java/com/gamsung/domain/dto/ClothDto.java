package com.gamsung.domain.dto;

import com.gamsung.domain.FemaleCloth;
import com.gamsung.domain.MaleCloth;
import com.gamsung.domain.RentalStatus;
import lombok.Data;

@Data
public class ClothDto {

    private MaleCloth maleCloth;
    private FemaleCloth femaleCloth;
    private RentalStatus rentalStatus;


    public ClothDto(MaleCloth maleCloth, FemaleCloth femaleCloth) {
        this.maleCloth = maleCloth;
        this.femaleCloth = femaleCloth;
    }
}
