package by.bsuir.suite.job.common;

import by.bsuir.suite.dao.duty.DutyDao;
import by.bsuir.suite.domain.duty.Duty;
import by.bsuir.suite.domain.duty.DutyStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author i.sukach
 */
public class CompleteDutiesJob implements HostelTask{

    @Autowired
    private DutyDao dutyDao;

    @Override
    public void execute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -5);

        List<Duty> unevaluatedDuties = dutyDao.getUnevaluatedDutiesBeforeDate(calendar.getTime());
        for (Duty duty : unevaluatedDuties) {
            duty.setStatus(DutyStatus.COMPLETED_GOOD);
            dutyDao.update(duty);
        }
    }
}
