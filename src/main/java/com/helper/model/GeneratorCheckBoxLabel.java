package com.helper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * <code>GeneratorCheckBoxLabel</code>
 * </p>
 * Description:
 *
 * @author Mcchu
 * @date 2018/3/6 11:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratorCheckBoxLabel {

    private boolean controllerRBtn;

    private boolean entityRBtnSelect;

    private boolean dtoRBtnSelect;

    private boolean serviceRBtnSelect;

    private boolean daoRBtnSelect;

    private boolean mapperRBtnSelect;
}
