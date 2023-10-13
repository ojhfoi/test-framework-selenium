package ojhfoi.core.listeners;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.extern.log4j.Log4j2;

import static io.qameta.allure.model.Status.*;

@Log4j2
public class StepsListener implements StepLifecycleListener {

    @Override
    public void beforeStepStart(StepResult result) {
        if (result.getName() != null) {
            log.info("=== Start step {} ===", result.getName());
        }
    }

    @Override
    public void beforeStepStop(StepResult result) {
        if (result.getName() != null) {
            if (result.getStatus() == FAILED || result.getStatus() == SKIPPED || result.getStatus() == BROKEN) {
                log.info("=== Step {} is {} ===", result.getName(), result.getStatus());
            }
        }
    }
}
