package adventure.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdventureTest.class,
        ArtifactTest.class,
        ContainerTest.class,
        LocationTest.class,
        NarratorTest.class
})
public class AllTests {
}
