package com.example.ReportPlayer.repository.helpCenter;

import com.example.ReportPlayer.models.helpCenter.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpCenterRepository extends JpaRepository<ContactUs,Long> {

}
