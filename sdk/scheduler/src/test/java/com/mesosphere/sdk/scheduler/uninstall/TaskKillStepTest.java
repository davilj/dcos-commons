package com.mesosphere.sdk.scheduler.uninstall;

import com.mesosphere.sdk.framework.Driver;
import com.mesosphere.sdk.scheduler.plan.Status;
import org.apache.mesos.Protos;
import org.apache.mesos.SchedulerDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class TaskKillStepTest {

    @Mock
    private SchedulerDriver driver;
    private Protos.TaskID taskID = Protos.TaskID.newBuilder().setValue("task-1").build();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Driver.setDriver(driver);
    }

    @Test
    public void testStart() {
        TaskKillStep step = createStep();
        Assert.assertEquals(Optional.empty(), step.start());
        Assert.assertEquals(Status.COMPLETE, step.getStatus());
        Mockito.verify(driver).killTask(taskID);
    }

    private TaskKillStep createStep() {
        TaskKillStep step = new TaskKillStep(taskID);
        return step;
    }
}
