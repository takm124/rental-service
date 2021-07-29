package com.gamsung.domain.dto;

import com.gamsung.domain.Extra;
import com.gamsung.domain.FemaleCloth;
import com.gamsung.domain.MaleCloth;
import com.gamsung.domain.RentalStatus;
import lombok.Data;

@Data
public class ClothDto {

    private MaleCloth maleCloth;
    private FemaleCloth femaleCloth;
    private Extra extra;

    public ClothDto(MaleCloth maleCloth, FemaleCloth femaleCloth, Extra extra) {
        this.maleCloth = maleCloth;
        this.femaleCloth = femaleCloth;
        this.extra = extra;
    }
}
