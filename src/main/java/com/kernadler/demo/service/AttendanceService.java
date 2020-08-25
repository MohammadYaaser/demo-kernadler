package com.kernadler.demo.service;

import com.kernadler.demo.model.Attendance;
import com.kernadler.demo.model.Employee;
import com.kernadler.demo.model.MonthlySalaries;
import com.kernadler.demo.repository.AttendanceRepository;
import com.kernadler.demo.repository.MonthlySalaryRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttendanceService {

  private AttendanceRepository attendanceRepository;
  private EmployeeService employeeService;
  private MonthlySalaryRepository monthlySalaryRepository;

  @Autowired
  public AttendanceService(AttendanceRepository attendanceRepository, EmployeeService employeeService, MonthlySalaryRepository monthlySalaryRepository) {
    this.attendanceRepository = attendanceRepository;
    this.employeeService = employeeService;
    this.monthlySalaryRepository = monthlySalaryRepository;
  }

  public Attendance saveAtt(MultipartFile reapExcelDataFile, Employee employee) throws Exception {

    Attendance attendance = new Attendance();
    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
    XSSFSheet worksheet = workbook.getSheetAt(0);

    XSSFRow row = worksheet.getRow(0);
    boolean present;
    int numberOfDaysPresent = 0;

    if (row.getCell(0).getStringCellValue().equals("Yes")) {
      attendance.setDay_1(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(1).getStringCellValue().equals("Yes")) {
      attendance.setDay_2(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(2).getStringCellValue().equals("Yes")) {
      attendance.setDay_3(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(3).getStringCellValue().equals("Yes")) {
      attendance.setDay_4(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(4).getStringCellValue().equals("Yes")) {
      attendance.setDay_5(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(5).getStringCellValue().equals("Yes")) {
      attendance.setDay_6(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(6).getStringCellValue().equals("Yes")) {
      attendance.setDay_7(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(7).getStringCellValue().equals("Yes")) {
      attendance.setDay_8(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(8).getStringCellValue().equals("Yes")) {
      attendance.setDay_9(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(9).getStringCellValue().equals("Yes")) {
      attendance.setDay_10(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(10).getStringCellValue().equals("Yes")) {
      attendance.setDay_11(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(11).getStringCellValue().equals("Yes")) {
      attendance.setDay_12(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(12).getStringCellValue().equals("Yes")) {
      attendance.setDay_13(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(13).getStringCellValue().equals("Yes")) {
      attendance.setDay_14(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(14).getStringCellValue().equals("Yes")) {
      attendance.setDay_15(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(15).getStringCellValue().equals("Yes")) {
      attendance.setDay_16(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(16).getStringCellValue().equals("Yes")) {
      attendance.setDay_17(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(17).getStringCellValue().equals("Yes")) {
      attendance.setDay_18(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(18).getStringCellValue().equals("Yes")) {
      attendance.setDay_19(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(19).getStringCellValue().equals("Yes")) {
      attendance.setDay_20(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(20).getStringCellValue().equals("Yes")) {
      attendance.setDay_21(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(21).getStringCellValue().equals("Yes")) {
      attendance.setDay_22(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(22).getStringCellValue().equals("Yes")) {
      attendance.setDay_23(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(23).getStringCellValue().equals("Yes")) {
      attendance.setDay_24(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(24).getStringCellValue().equals("Yes")) {
      attendance.setDay_25(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(25).getStringCellValue().equals("Yes")) {
      attendance.setDay_26(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(26).getStringCellValue().equals("Yes")) {
      attendance.setDay_27(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(27).getStringCellValue().equals("Yes")) {
      attendance.setDay_28(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(28).getStringCellValue().equals("Yes")) {
      attendance.setDay_29(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(29).getStringCellValue().equals("Yes")) {
      attendance.setDay_30(true);
      numberOfDaysPresent++;
    }
    if (row.getCell(30).getStringCellValue().equals("Yes")) {
      attendance.setDay_31(true);
      numberOfDaysPresent++;
    }

    MonthlySalaries monthlySalaries = new MonthlySalaries();

    float employeeSalary;
    if (numberOfDaysPresent > 21) {
      employeeSalary = employee.getBaseSalary();
    }else{
      float salaryOfDay = employee.getBaseSalary() / 21.0f;
      employeeSalary = salaryOfDay * numberOfDaysPresent;
    }
    employeeSalary = employeeSalary / 12;
    monthlySalaries.setSalary((long) employeeSalary);

    int year = Integer.parseInt(row.getCell(31).getRawValue());

    attendance.setYear(year);
    attendance.setMonth(row.getCell(32).getStringCellValue());

    attendance.setEmpAttendance(employee);
    attendance.setMonthlySalary(monthlySalaries);

    monthlySalaryRepository.save(monthlySalaries);

    return attendanceRepository.save(attendance);
  }

  public float getEmployeeMonthlySalary(Attendance attendance) {
    return (float) attendance.getMonthlySalary().getSalary();
  }

}
