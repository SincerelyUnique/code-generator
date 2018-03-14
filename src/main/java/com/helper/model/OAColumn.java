
package com.helper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * <code>OAColumn</code>
 * </p>
 * Description:
 *
 * @author Mcchu
 * @date 2018/3/7 13:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAColumn {

    private String columnName;

    private String dataType;

    private String columnLength;

    private String columnPrecision;

    private String columnScale;
}
