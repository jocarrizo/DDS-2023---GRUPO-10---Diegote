package persistance;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.newJob;


public class Programador{

    public static void programar() throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = newJob(Actualizador.class)
                .withIdentity("ActualizarDB", "group1")
                .build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("DispararActualizacion", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 13 * * SUN"))
                .forJob("ActualizarDB", "group1")
                .build();

        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

}
