package com.example.ReportPlayer.services.report.description;

import com.example.ReportPlayer.dto.report.DescriptionReportDto;
import com.example.ReportPlayer.exception.EntityNotExistException;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.repository.report.description.DescriptionReportBaseRepository;
import javassist.runtime.Desc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DescriptionReportServiceImpl implements DescriptionReportService {

    private DescriptionReportBaseRepository repository;

    public DescriptionReportServiceImpl(DescriptionReportBaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public DescriptionReport save(DescriptionReport descriptionReport) {
        descriptionReport.setDate(new Date());
        return (DescriptionReport) repository.save(descriptionReport);
    }

    @Override
    public void deleteById(long id) {
        final DescriptionReport descriptionReportExpect = this.findById(id);
        repository.delete(descriptionReportExpect);
    }

    @Override
    public DescriptionReport findById(long id) {
        final Optional<DescriptionReport > descriptionReportExpect = repository.findById(id);
        if(!descriptionReportExpect.isPresent()) {
            throw new EntityNotExistException("report not exist ");
        }
        return descriptionReportExpect.get();
    }

    @Override
    public List<DescriptionReport> getTheLast10DescriptionReport() {
        List<DescriptionReport> descriptionReports = repository.findTop10ByOrderByDateDesc();
        return descriptionReports;
    }

    @Override
    public List<DescriptionReport> getDescriptionReportsByPlayerId(long id) {
        List<DescriptionReport> pageResult = repository.findAllByPlayerId(id);
        return pageResult;
    }
}
