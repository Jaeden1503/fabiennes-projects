package com.example.siouxmanagementsystem.persistence;

import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Long> {
    List<AppointmentEntity> findAppointmentEntitiesByEmployeeId(Long empId);
    List<AppointmentEntity> findAllByGuest_License(String license);
    List<AppointmentEntity> findAllByGuest_Name(String name);
    @Query("select a from AppointmentEntity a where a.guest.license = ?1 and a.date = ?2")
    AppointmentEntity getAppointmentByLicenseAndDate(String license, LocalDate date);

    @Query("select a from AppointmentEntity a where a.date between ?1 and ?2")
    List<AppointmentEntity> findAppointmentEntitiesByDate(LocalDate startDate, LocalDate endDate);

    @Query("select a from AppointmentEntity a where a.employee.id= ?1 and a.date= ?2")
    List<AppointmentEntity> findAppointmentEntitiesByEmpIdAndDate(Long empId, LocalDate date);

    @Query("select a.guest.license from AppointmentEntity a where a.date= ?1")
    List<String> findAllLicensePlatesByDate(LocalDate date);
}
