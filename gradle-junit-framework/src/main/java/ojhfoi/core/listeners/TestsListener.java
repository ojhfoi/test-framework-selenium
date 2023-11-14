package ojhfoi.core.listeners;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Log4j2
public class TestsListener implements TestWatcher, AfterAllCallback {

    private List<TestResultStatus> testResultsStatus = new ArrayList<>();

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED;
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
//        TestWatcher.super.testSuccessful(context);
        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
        log.info("+++ Test " + context.getDisplayName() + " is successful +++");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
//        TestWatcher.super.testFailed(context, cause);
        testResultsStatus.add(TestResultStatus.FAILED);
        log.info("+++ Test " + context.getDisplayName() + " is failed +++");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        log.info("Test result summary for {} {}", context.getDisplayName(), summary.toString());
    }
}
