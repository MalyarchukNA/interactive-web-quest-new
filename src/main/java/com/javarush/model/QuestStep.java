package com.javarush.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestStep {
    private String id;

    private String prevText;    //Текст, в зависимости от выбора на предыдущем слайде
    private String text;

    private String nextStep1id;
    private String option1;
    private Integer fragment1id;
    private Integer lucidity1id;
    private String nextStepText1id;

    private String nextStep2id;
    private String option2;
    private Integer fragment2id;
    private Integer lucidity2id;
    private String nextStepText2id;


    //For finals
    public QuestStep(String id, String text) {
        this.id = id;
        this.text = text;
    }

}
