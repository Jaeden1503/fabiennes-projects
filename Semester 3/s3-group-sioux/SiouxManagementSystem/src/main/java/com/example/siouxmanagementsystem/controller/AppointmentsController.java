package com.example.siouxmanagementsystem.controller;

import com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases.CreateAppointmentUseCase;
import com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases.DeleteAppointmentUseCase;
import com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases.GetAppointmentsUseCase;
import com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases.UpdateAppointmentUseCase;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentRequest;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentResponse;
import com.example.siouxmanagementsystem.domain.Appointment.GetAppointmentsResponse;
import com.example.siouxmanagementsystem.domain.Appointment.UpdateAppointmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AppointmentsController {
    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final GetAppointmentsUseCase getAppointmentsUseCase;
    private final DeleteAppointmentUseCase deleteAppointmentUseCase;
    private final UpdateAppointmentUseCase updateAppointmentUseCase;

    @PostMapping()
    public ResponseEntity<CreateAppointmentResponse> createAppointment(@RequestBody @Valid CreateAppointmentRequest request)
    {
        CreateAppointmentResponse response = createAppointmentUseCase.CreateAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/employee/{empId}")
    @Transactional(readOnly = true)
    public ResponseEntity<GetAppointmentsResponse> getAppointmentByEmpId(@PathVariable long empId)
    {
        GetAppointmentsResponse response = getAppointmentsUseCase.getAppointmentsByEmpId(empId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/employee/empIdAndDate")
    @Transactional(readOnly = true)
    public ResponseEntity<GetAppointmentsResponse> getAppointmentByEmpIdAndDate(@RequestParam(value = "date", required = true) String date ,
                                                                                @RequestParam(value = "empId", required = true) long empId)
    {
        GetAppointmentsResponse response = getAppointmentsUseCase.getAppointmentsByEmpIdAndDate(empId,LocalDate.parse(date));
        return ResponseEntity.ok(response);
    }
    @GetMapping("/license/{licensePlate}")
    public ResponseEntity<GetAppointmentsResponse> getAppointmentByLicensePlate(@PathVariable String licensePlate)
    {
        GetAppointmentsResponse response = getAppointmentsUseCase.getAppointmentsByGuestLicense(licensePlate);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/guest/{guestName}")
    public ResponseEntity<GetAppointmentsResponse> getAppointmentByGuestName(@PathVariable String guestName)
    {
        GetAppointmentsResponse response = getAppointmentsUseCase.getAppointmentsByGuestName(guestName);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int appointmentId) {
        deleteAppointmentUseCase.deleteAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable("id") long id,
                                                  @RequestBody @Valid UpdateAppointmentRequest request) {
        request.setId(id);
        updateAppointmentUseCase.updateAppointment(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/date")
    public ResponseEntity<GetAppointmentsResponse> getAppointmentsByDate(@RequestParam(value = "startDate", required = true) String startDate,
                                                                          @RequestParam(value = "endDate", required = true) String endDate
    ) {
        GetAppointmentsResponse response = getAppointmentsUseCase.getAppointmentsBetweenDates(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(response);
    }

}
