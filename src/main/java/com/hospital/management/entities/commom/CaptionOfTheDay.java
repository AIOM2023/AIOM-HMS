package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_caption_of_the_day")
public class CaptionOfTheDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "caption_of_the_day_id")
    private Integer captionOfTheDayId;

    @Column(name= "caption_of_the_day_code")
    private String captionOfTheDayCode;

    @Column(name= "caption_of_the_day_value")
    private String captionOfTheDayValue;

    @Column(name= "caption_of_the_day_description")
    private String captionOfTheDayDescription;
}
