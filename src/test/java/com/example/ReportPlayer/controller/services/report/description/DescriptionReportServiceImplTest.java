package com.example.ReportPlayer.controller.services.report.description;

import com.example.ReportPlayer.enumerated.ReportType;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.exception.EntityNotExistException;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.report.description.DescriptionReportBaseRepository;
import com.example.ReportPlayer.services.report.description.DescriptionReportServiceImpl;
import com.example.ReportPlayer.builder.report.description.DescriptionReportBuilder;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DescriptionReportServiceImplTest {


    @InjectMocks
    @Qualifier("description_service_"+ Server.Region.EUW)
    private DescriptionReportServiceImpl service;
    @Mock
    private DescriptionReportBaseRepository repository;
    @Mock
    private ApplicationContext applicationContext;


    @Test
    public void save_ShouldSave() {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo1").build();
        final DescriptionReport descriptionReport = DescriptionReportBuilder.newBuilder().description("it was afk").region(Server.Region.EUW).
                player(player).reportType(ReportType.AFK.toString()).user(user).build();
        //act
        when(repository.save(descriptionReport)).thenReturn(descriptionReport);
        final DescriptionReport descriptionReportExpected = service.save(descriptionReport);
        //assert
        verify(repository,times(1)).save(descriptionReport);
        assertNotNull(descriptionReportExpected.getPlayer());
        assertEquals("Apollo1",descriptionReportExpected.getPlayer().getNickname());
        assertEquals("it was afk",descriptionReportExpected.getDescription());
        assertEquals(ReportType.AFK.toString(),descriptionReportExpected.getReportType());
        assertEquals(descriptionReport.getClass().getSimpleName(),descriptionReportExpected.getClass().getSimpleName());
        assertEquals(user,descriptionReportExpected.getUser());
    }

    @Test
    public void delete_ShouldDeleteById()  throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo1").build();
        final DescriptionReport descriptionReport = DescriptionReportBuilder.newBuilder().description("it was afk").region(Server.Region.EUW).
                player(player).reportType(ReportType.AFK.toString()).build();
        long id = 1l;
        //act
        when(repository.findById(anyLong())).thenReturn(Optional.of(descriptionReport));
        doNothing().when(repository).delete(descriptionReport);
        service.deleteById(id);
        //assert
        verify(repository,times(1)).findById(anyLong());
        verify(repository,times(1)).delete(descriptionReport);
    }

    @Test
    public void delete_ShouldThrowExceptionIfNotExist()  throws Exception {
        //arrange
        //act
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotExistException.class, () -> {
           service.deleteById(1L);
        });
        //assert
        verify(repository,times(1)).findById(anyLong());
        verify(repository,times(0)).delete(any(Object.class));


    }

    @Test
    public void find_ShouldFindById() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo1").build();
        final DescriptionReport descriptionReport = DescriptionReportBuilder.newBuilder().description("it was afk").region(Server.Region.EUW).
                player(player).reportType(ReportType.AFK.toString()).build();
        //act
        when(repository.findById(anyLong())).thenReturn(Optional.of(descriptionReport));
        final DescriptionReport descriptionReportExpected = service.findById(1);
        //assert
        assertNotNull(descriptionReportExpected);
    }

    @Test
    public void find_ShouldThrowsExceptionIfReportNotExist() throws Exception {
        //arrange
        //act
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotExistException.class,()->{
            final DescriptionReport descriptionReportExpected = service.findById(1);
            //assert
            assertNull(descriptionReportExpected);
        });

    }


}
