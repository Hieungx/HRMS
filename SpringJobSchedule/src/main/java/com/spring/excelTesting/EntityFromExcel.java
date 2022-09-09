package com.spring.excelTesting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityFromExcel {
    String ldap;
    String date;
    String time;
    String description;
}
