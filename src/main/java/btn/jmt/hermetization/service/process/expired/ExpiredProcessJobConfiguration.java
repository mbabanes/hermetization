package btn.jmt.hermetization.service.process.expired;

import org.quartz.JobDetail;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

@Configuration
@ConditionalOnProperty(
    value = "process.expired-job.enabled",
    havingValue = "true",
    matchIfMissing = true)
class ExpiredProcessJobConfiguration {

  @Bean
  JobDetailFactoryBean expiredProcessJob() {
    var jobDetailFactory = new JobDetailFactoryBean();
    jobDetailFactory.setJobClass(ExpiredProcessJob.class);
    jobDetailFactory.setDescription("Invoke Sample Job service...");
    jobDetailFactory.setDurability(true);
    return jobDetailFactory;
  }

  @Bean
  CronTriggerFactoryBean trigger(JobDetail expiredProcessJob) {
    var trigger = new CronTriggerFactoryBean();
    trigger.setJobDetail(expiredProcessJob);
    trigger.setCronExpression("0/30 * * * * ? *");

    return trigger;
  }
}
