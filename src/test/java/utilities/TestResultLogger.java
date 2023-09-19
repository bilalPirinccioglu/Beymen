package utilities;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TestResultLogger extends TestWatcher {

    Log log = new Log();
    @Override
    protected void succeeded(Description description) {
        String testname = description.getDisplayName();
        log.info(testname + " PASSED");
    }

    @Override
    protected void failed(Throwable e, Description description) {
        String testname = description.getDisplayName();
        log.info(testname + " FAILED");
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        String testname = description.getDisplayName();
        log.info(testname + " SKIPPED");
    }

    @Override
    protected void starting(Description description) {
        String testname = description.getDisplayName();
        log.info(testname + " is starting");
    }

    @Override
    protected void finished(Description description) {
        String testname = description.getDisplayName();
        log.info(testname + " Finished");
    }
}
