package com.example.siouxmanagementsystem.business.impl;

import com.example.siouxmanagementsystem.business.UseCases.LicensePlateUseCase;
import com.example.siouxmanagementsystem.business.impl.SMS.TwilioSmsSender;
import com.example.siouxmanagementsystem.domain.Sms.SmsRequest;
import com.example.siouxmanagementsystem.persistence.AppointmentRepository;
import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LicensePlateUseCaseImpl implements LicensePlateUseCase {
    private final AppointmentRepository appointmentRepository;
    private final SendEmailUseCaseImpl emailCreationUseCase;
    private final CheckLicenseSpelling checkLicenseSpelling;
    private final SensorAvailabilityImpl sensorAvailability;
    private final TwilioSmsSender twilioSmsSender;

    @Transactional
    @Override
    public String compareLicense(String request) {
        LocalDate date = LocalDate.of(2023, 7, 1); //TODO: should be date of today

        List<String> licenses = appointmentRepository.findAllLicensePlatesByDate(date);
        HashMap<String, Integer> map = new HashMap<>();
        for (String plate : licenses) {
            map.put(plate, 1);
        }

        String license = checkLicenseSpelling.correct(request, map);
        //System.out.println(license);
        Optional<AppointmentEntity> newAppointment = Optional.ofNullable(appointmentRepository.getAppointmentByLicenseAndDate(license, date));
        //System.out.println(newAppointment.get().getGuest().getName());

        if (newAppointment.isEmpty()) {
            return "no matching license found";
        }

        String guestName = newAppointment.get().getGuest().getName();
        if (sensorAvailability.checkAvailibility()) { //free spot
            System.out.println("free spot");
            emailCreationUseCase.createEmail(newAppointment.get().getEmployee().getEmailAddress(), guestName + " will arrive shortly");
        }
        else { //no free spots
            emailCreationUseCase.createEmail(newAppointment.get().getEmployee().getEmailAddress(), guestName + " has to go to another parking");
            System.out.println("no free spot");

            SmsRequest smsRequest = new SmsRequest("", "The parking is full. Go to https://goo.gl/maps/oYG1MVvtrrfcEpXn8");
            twilioSmsSender.sendSms(smsRequest);
        }

        return "found matching license";
    }
}
