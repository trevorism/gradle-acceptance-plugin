package com.trevorism.acceptance.plugin.util

import com.google.gson.Gson
import org.junit.Test

/**
 * @author tbrooks
 */
class ResultParserTest {

    @Test
    void testParseError(){
        List<TestResult> results = ResultParser.parseResult(SampleDataProvider.provideError())
        Gson gson = new Gson()
        String json = gson.toJson(results[3])
        assert json.contains("Context Root of Datastore")
        assert json.contains("Ping on app engine")
        assert json.contains("Given the datastore application is alive")

    }

    @Test
    void testParseResult() {
        List<TestResult> results = ResultParser.parseResult(SampleDataProvider.provideGivenWhenThen())

        assert results
        assert results.size() == 4
        assert firstTestResult() == results[0]
        assert fourthTestResult() == results[3]
    }

    @Test
    void testParseResultWithAnds() {
        List<TestResult> results = ResultParser.parseResult(SampleDataProvider.provideGivenAndAnd())

        assert results
        assert results.size() == 3
        assert andTestResult() == results[0]
    }

    private static TestResult andTestResult() {
        new TestResult( "Crud on an object", "Create a test object",
                "Given the datastore application is alive And a test object is defined And the object is created",
                "",
                "Then the object can found by listing all objects And the object can be retrieved by id",
                true, 1653)
    }

    private static TestResult firstTestResult() {
        new TestResult("Context Root of Datastore", "ContextRoot",
                "Given the datastore application is alive",
                "When I navigate to \"http://datastore.trevorism.com\"",
        "Then the API returns an array, letting me know where I can go next",
        true, 476)

    }


    private static TestResult fourthTestResult() {
        new TestResult("Context Root of Datastore", "Ping on app engine",
                "Given the datastore application is alive",
                "When I navigate to /ping on \"https://trevorism-gcloud.appspot.com\"",
                "Then pong is returned, to indicate the service is alive",
                false, 150)

    }
}
