package com.example.ReportPlayer.services.helpCenter;

import com.example.ReportPlayer.dto.request.ContactUsDto;
import com.example.ReportPlayer.models.helpCenter.ContactUs;
import com.example.ReportPlayer.repository.helpCenter.HelpCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelpCenterServiceImpl implements HelpCenterService{


    @Autowired
    private HelpCenterRepository helpCenterRepository;

    @Override
    public void contactUs(ContactUsDto contactUsDto) {
        ContactUs contactUs = new ContactUs(contactUsDto.getType(),contactUsDto.getTitle(),contactUsDto.getDescription(),contactUsDto.getUser());
        helpCenterRepository.save(contactUs);
    }

}
