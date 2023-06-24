
package com.example.siouxmanagementsystem.configuration.db;

import com.example.siouxmanagementsystem.domain.Secretary.CreateSecretaryRequest;
import com.example.siouxmanagementsystem.persistence.*;
import com.example.siouxmanagementsystem.persistence.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

@Component
@AllArgsConstructor
public class DatabaseInitializer {
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private AppointmentRepository appointmentRepository;
    private GuestRepository guestRepository;
    private static final String USERNAME_SUFFIX = "@sioux.nl";
    private SecretaryRepository secretaryRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        if (secretaryRepository.count() == 0) {
            SecretaryEntity secretary = secretaryRepository.save(SecretaryEntity.builder().name("alaa").build());
            String encodedPassword = passwordEncoder.encode("123");
            UserEntity newUser = UserEntity.builder()
                    .username("alaa" + USERNAME_SUFFIX)
                    .password(encodedPassword)
                    .secretary(secretary)
                    .build();
            newUser.setUserRoles(Set.of(
                    UserRoleEntity.builder()
                            .User(newUser)
                            .role(RoleEnum.SECRETARY)
                            .build()));
            userRepository.save(newUser);
        }
        if (departmentRepository.count() == 0) {
            departmentRepository.save(DepartmentEntity.builder().name("IT").build());
            departmentRepository.save(DepartmentEntity.builder().name("Finance").build());
        }
        if (employeeRepository.count() == 0) {
            employeeRepository.save(EmployeeEntity.builder()
                    .name("Aya")
                    .emailAddress("aya@gmail.com")
                    .department(DepartmentEntity.builder().id(1L).build())
                    .build());
            employeeRepository.save(EmployeeEntity.builder()
                    .name("Alaa")
                    .emailAddress("alaa@gmail.com")
                    .department(DepartmentEntity.builder().id(2L).build())
                    .build());
            employeeRepository.save(EmployeeEntity.builder()
                    .name("Rob")
                    .emailAddress("rob@gmail.com")
                    .department(DepartmentEntity.builder().id(2L).build())
                    .build());
            employeeRepository.save(EmployeeEntity.builder()
                    .name("Fabienne")
                    .emailAddress("fabienne@gmail.com")
                    .department(DepartmentEntity.builder().id(1L).build())
                    .build());
        }
        if (guestRepository.count() == 0) {
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Emily Johnson")
                            .email_address("emily@gmail.com")
                            .license("RTX575")
                            .phone_number("+31684121634")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Daniel Smith")
                            .email_address("daniel@gmail.com")
                            .license("ACB312")
                            .phone_number("0692359684")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Olivia Davis")
                            .email_address("olivia@gmail.com")
                            .license("ABC321")
                            .phone_number("0626598465")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Ethan Anderson")
                            .email_address("ethan@gmail.com")
                            .license("F97D12")
                            .phone_number("0625984163")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Sophia Martinez")
                            .email_address("sophia@gmail.com")
                            .license("LK42B9")
                            .phone_number("0664575687")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Liam Taylor")
                            .email_address("liam@gmail.com")
                            .license("QW4S2G")
                            .phone_number("0621346559")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Ava Thompson")
                            .email_address("ava@gmail.com")
                            .license("B52KL3")
                            .phone_number("0610293874")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Noah Rodriguez")
                            .email_address("noah@gmail.com")
                            .license("A9OKF2")
                            .phone_number("0659574820")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Isabella Clark")
                            .email_address("isabella@gmail.com")
                            .license("Y5FG13")
                            .phone_number("0690782156")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Aiden Lewis")
                            .email_address("aiden@gmail.com")
                            .license("Y5FG44")
                            .phone_number("0613355768")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Mia Walker")
                            .email_address("mia@gmail.com")
                            .license("A41N4R")
                            .phone_number("0613354635")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Lucas Hall")
                            .email_address("lucas@gmail.com")
                            .license("A932BA")
                            .phone_number("0642367485")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Harper Young")
                            .email_address("harper@gmail.com")
                            .license("A1C23B")
                            .phone_number("0652364645")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Benjamin Wright")
                            .email_address("benjamin@gmail.com")
                            .license("AC10B3")
                            .phone_number("0654654630")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Amelia Hernandez")
                            .email_address("amelia@gmail.com")
                            .license("AZ98S9")
                            .phone_number("0665767584")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Alexander Turner")
                            .email_address("alexander@gmail.com")
                            .license("ACB315")
                            .phone_number("0601923020")
                            .build()
            );
            guestRepository.save(
                    GuestEntity.builder()
                            .name("Chloe Mitchell")
                            .email_address("chloe@gmail.com")
                            .license("A098FG")
                            .phone_number("0654902190")
                            .build()
            );
        }
        if (appointmentRepository.count() == 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(9, 00, 00).format(formatter))
                            .end_time(LocalTime.of(10, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 07, 01))
                            .employee(employeeRepository.findById(2L).get())
                            .guest(guestRepository.findGuestEntityByLicense("RTX575").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(01, 30, 00).format(formatter))
                            .date(LocalDate.of(2023, 04, 02))
                            .employee(employeeRepository.findById(1L).get())
                            .guest(guestRepository.findGuestEntityByLicense("ACB312").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(10, 00, 00).format(formatter))
                            .end_time(LocalTime.of(11, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 04, 05))
                            .employee(employeeRepository.findById(2L).get())
                            .guest(guestRepository.findGuestEntityByLicense("ABC321").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(01, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 04, 05))
                            .employee(employeeRepository.findById(3L).get())
                            .guest(guestRepository.findGuestEntityByLicense("F97D12").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(11, 30, 00).format(formatter))
                            .end_time(LocalTime.of(12, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 04, 06))
                            .employee(employeeRepository.findById(4L).get())
                            .guest(guestRepository.findGuestEntityByLicense("LK42B9").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(11, 30, 00).format(formatter))
                            .end_time(LocalTime.of(12, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 05, 06))
                            .employee(employeeRepository.findById(4L).get())
                            .guest(guestRepository.findGuestEntityByLicense("QW4S2G").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(14, 30, 00).format(formatter))
                            .end_time(LocalTime.of(15, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 05, 07))
                            .employee(employeeRepository.findById(4L).get())
                            .guest(guestRepository.findGuestEntityByLicense("B52KL3").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(11, 30, 00).format(formatter))
                            .end_time(LocalTime.of(12, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 05, 07))
                            .employee(employeeRepository.findById(4L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A9OKF2").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(13, 30, 00).format(formatter))
                            .date(LocalDate.of(2023, 05, 9))
                            .employee(employeeRepository.findById(4L).get())
                            .guest(guestRepository.findGuestEntityByLicense("Y5FG13").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(11, 30, 00).format(formatter))
                            .end_time(LocalTime.of(12, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 05, 10))
                            .employee(employeeRepository.findById(3L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A41N4R").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(11, 30, 00).format(formatter))
                            .end_time(LocalTime.of(12, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 05, 12))
                            .employee(employeeRepository.findById(2L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A932BA").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(11, 30, 00).format(formatter))
                            .end_time(LocalTime.of(12, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 05, 16))
                            .employee(employeeRepository.findById(1L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A1C23B").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(11, 30, 00).format(formatter))
                            .end_time(LocalTime.of(12, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 05, 18))
                            .employee(employeeRepository.findById(2L).get())
                            .guest(guestRepository.findGuestEntityByLicense("AC10B3").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(13, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 06, 20))
                            .employee(employeeRepository.findById(2L).get())
                            .guest(guestRepository.findGuestEntityByLicense("AZ98S9").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(11, 30, 00).format(formatter))
                            .end_time(LocalTime.of(12, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 06, 22))
                            .employee(employeeRepository.findById(2L).get())
                            .guest(guestRepository.findGuestEntityByLicense("ACB315").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(13, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 06, 22))
                            .employee(employeeRepository.findById(2L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A098FG").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(13, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 06, 25))
                            .employee(employeeRepository.findById(1L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A098FG").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(13, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 06, 28))
                            .employee(employeeRepository.findById(3L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A098FG").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(13, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 07, 02))
                            .employee(employeeRepository.findById(4L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A098FG").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(13, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 07, 05))
                            .employee(employeeRepository.findById(3L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A098FG").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(13, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 07, 06))
                            .employee(employeeRepository.findById(4L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A098FG").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(12, 30, 00).format(formatter))
                            .end_time(LocalTime.of(13, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 07, 06))
                            .employee(employeeRepository.findById(2L).get())
                            .guest(guestRepository.findGuestEntityByLicense("ACB315").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(10, 30, 00).format(formatter))
                            .end_time(LocalTime.of(11, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 05, 30))
                            .employee(employeeRepository.findById(4L).get())
                            .guest(guestRepository.findGuestEntityByLicense("A41N4R").get())
                            .build()
            );
            appointmentRepository.save(
                    AppointmentEntity.builder()
                            .start_time(LocalTime.of(11, 30, 00).format(formatter))
                            .end_time(LocalTime.of(12, 00, 00).format(formatter))
                            .date(LocalDate.of(2023, 07, 01))
                            .employee(employeeRepository.findById(2L).get())
                            .guest(guestRepository.findGuestEntityByLicense("QW4S2G").get())
                            .build()
            );
        }
    }
}