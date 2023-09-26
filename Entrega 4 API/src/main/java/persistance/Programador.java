package persistance;
import org.eclipse.jetty.util.thread.Scheduler;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Programador{

    public static void main(String[] args) throws SchedulerException {
        // Obtén el planificador de Quartz
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // Inicia el planificador
        scheduler.start();

        // Define el JobDetail que especifica la clase Actualizador
        JobDetail jobDetail = JobBuilder.newJob(Actualizador.class)
                .withIdentity("actualizadorJob", "grupo1")
                .build();

        // Define el disparador (Trigger) que programa la ejecución del Job todos los domingos a las 13 horas
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("domingoTrigger", "grupo1")
                .withSchedule(CronScheduleBuilder.weeklyOnDayAndHourAndMinute(Calendar.SUNDAY, 13, 0))
                .build();

        // Programa el Job con el Trigger
        scheduler.scheduleJob(jobDetail, trigger);
    }
}