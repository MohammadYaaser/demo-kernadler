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
@Table(name = "monthly_salary")
public class MonthlySalaries {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "sal_id")
  private Integer id;

  @Column(name = "salary")
  private Long salary;

}
