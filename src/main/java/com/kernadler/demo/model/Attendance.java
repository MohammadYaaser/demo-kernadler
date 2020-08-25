package com.kernadler.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "att_id")
  private Integer id;

  @Column(name = "month")
  private String month;

  @Column(name = "year")
  private Integer year;

  @Column(name = "day_1")
  private boolean day_1;

  @Column(name = "day_2")
  private boolean day_2;

  @Column(name = "day_3")
  private boolean day_3;

  @Column(name = "day_4")
  private boolean day_4;

  @Column(name = "day_5")
  private boolean day_5;

  @Column(name = "day_6")
  private boolean day_6;

  @Column(name = "day_7")
  private boolean day_7;

  @Column(name = "day_8")
  private boolean day_8;

  @Column(name = "day_9")
  private boolean day_9;

  @Column(name = "day_10")
  private boolean day_10;

  @Column(name = "day_11")
  private boolean day_11;

  @Column(name = "day_12")
  private boolean day_12;

  @Column(name = "day_13")
  private boolean day_13;

  @Column(name = "day_14")
  private boolean day_14;

  @Column(name = "day_15")
  private boolean day_15;

  @Column(name = "day_16")
  private boolean day_16;

  @Column(name = "day_17")
  private boolean day_17;

  @Column(name = "day_18")
  private boolean day_18;

  @Column(name = "day_19")
  private boolean day_19;

  @Column(name = "day_20")
  private boolean day_20;

  @Column(name = "day_21")
  private boolean day_21;

  @Column(name = "day_22")
  private boolean day_22;

  @Column(name = "day_23")
  private boolean day_23;

  @Column(name = "day_24")
  private boolean day_24;

  @Column(name = "day_25")
  private boolean day_25;

  @Column(name = "day_26")
  private boolean day_26;

  @Column(name = "day_27")
  private boolean day_27;

  @Column(name = "day_28")
  private boolean day_28;

  @Column(name = "day_29")
  private boolean day_29;

  @Column(name = "day_30")
  private boolean day_30;

  @Column(name = "day_31")
  private boolean day_31;


  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinTable(
      name = "emp_attendance",
      joinColumns = @JoinColumn(name = "att_id"),
      inverseJoinColumns = @JoinColumn(name = "emp_id"))
  private Employee empAttendance;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinTable(
      name = "attendance_salary",
      joinColumns = @JoinColumn(name = "att_id"),
      inverseJoinColumns = @JoinColumn(name = "sal_id"))
  private MonthlySalaries monthlySalary;

}
