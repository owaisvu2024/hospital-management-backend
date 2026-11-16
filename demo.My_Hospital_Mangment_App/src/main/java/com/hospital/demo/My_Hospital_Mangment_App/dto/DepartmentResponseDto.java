package com.hospital.demo.My_Hospital_Mangment_App.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DepartmentResponseDto {
    private Long departId;
    private String  departName;
    private LocalDateTime creatAt;
    private Long doctorHeadId;

}
