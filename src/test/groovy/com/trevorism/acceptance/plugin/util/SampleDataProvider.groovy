package com.trevorism.acceptance.plugin.util

/**
 * @author tbrooks
 */
class SampleDataProvider {
    private SimpleDataProvider(){}

    static String provideGivenAndAnd(){
        return """[
  {
    "line": 1,
    "elements": [
      {
        "line": 5,
        "name": "Create a test object",
        "description": "",
        "id": "crud-on-an-object;create-a-test-object",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 463305594,
              "status": "passed"
            },
            "line": 6,
            "name": "the datastore application is alive",
            "match": {
              "location": "ContextRootStepDefinition.groovy:8"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 16906625,
              "status": "passed"
            },
            "line": 7,
            "name": "a test object is defined",
            "match": {
              "location": "DatastoreStepDefinition.groovy:11"
            },
            "keyword": "And "
          },
          {
            "result": {
              "duration": 621372031,
              "status": "passed"
            },
            "line": 8,
            "name": "the object is created",
            "match": {
              "location": "DatastoreStepDefinition.groovy:15"
            },
            "keyword": "And "
          },
          {
            "result": {
              "duration": 309568996,
              "status": "passed"
            },
            "line": 9,
            "name": "the object can found by listing all objects",
            "match": {
              "location": "DatastoreStepDefinition.groovy:19"
            },
            "keyword": "Then "
          },
          {
            "result": {
              "duration": 241891009,
              "status": "passed"
            },
            "line": 10,
            "name": "the object can be retrieved by id",
            "match": {
              "location": "DatastoreStepDefinition.groovy:26"
            },
            "keyword": "And "
          }
        ]
      },
      {
        "line": 12,
        "name": "Update a test object",
        "description": "",
        "id": "crud-on-an-object;update-a-test-object",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 97667143,
              "status": "passed"
            },
            "line": 13,
            "name": "the datastore application is alive",
            "match": {
              "location": "ContextRootStepDefinition.groovy:8"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 188588,
              "status": "passed"
            },
            "line": 14,
            "name": "a test object is defined",
            "match": {
              "location": "DatastoreStepDefinition.groovy:11"
            },
            "keyword": "And "
          },
          {
            "result": {
              "duration": 199119411,
              "status": "passed"
            },
            "line": 15,
            "name": "the object is created",
            "match": {
              "location": "DatastoreStepDefinition.groovy:15"
            },
            "keyword": "And "
          },
          {
            "result": {
              "duration": 318142772,
              "status": "passed"
            },
            "line": 16,
            "name": "the object is updated",
            "match": {
              "location": "DatastoreStepDefinition.groovy:31"
            },
            "keyword": "When "
          },
          {
            "result": {
              "duration": 180418785,
              "status": "passed"
            },
            "line": 17,
            "name": "the object can be retrieved by id",
            "match": {
              "location": "DatastoreStepDefinition.groovy:26"
            },
            "keyword": "Then "
          },
          {
            "result": {
              "duration": 432643,
              "status": "passed"
            },
            "line": 18,
            "name": "the object reflects the update",
            "match": {
              "location": "DatastoreStepDefinition.groovy:36"
            },
            "keyword": "And "
          }
        ]
      },
      {
        "line": 20,
        "name": "Delete a test object",
        "description": "",
        "id": "crud-on-an-object;delete-a-test-object",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 186875428,
              "status": "passed"
            },
            "line": 21,
            "name": "the datastore application is alive",
            "match": {
              "location": "ContextRootStepDefinition.groovy:8"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 193992,
              "status": "passed"
            },
            "line": 22,
            "name": "a test object is defined",
            "match": {
              "location": "DatastoreStepDefinition.groovy:11"
            },
            "keyword": "And "
          },
          {
            "result": {
              "duration": 211104550,
              "status": "passed"
            },
            "line": 23,
            "name": "the object is created",
            "match": {
              "location": "DatastoreStepDefinition.groovy:15"
            },
            "keyword": "And "
          },
          {
            "result": {
              "duration": 1094940442,
              "status": "passed"
            },
            "line": 24,
            "name": "every object in the list is deleted",
            "match": {
              "location": "DatastoreStepDefinition.groovy:41"
            },
            "keyword": "When "
          },
          {
            "result": {
              "duration": 195774894,
              "status": "passed"
            },
            "line": 25,
            "name": "no objects will exist when listing all objects",
            "match": {
              "location": "DatastoreStepDefinition.groovy:48"
            },
            "keyword": "Then "
          }
        ]
      }
    ],
    "name": "Crud on an object",
    "description": "\\r\\nI can perform CRUD and List on an arbitrary object",
    "id": "crud-on-an-object",
    "keyword": "Feature",
    "uri": "features/Datastore.feature"
  }
]"""
    }

    static String provideGivenWhenThen(){
      return """[
  {
    "line": 1,
    "elements": [
      {
        "line": 4,
        "name": "ContextRoot",
        "description": "",
        "id": "context-root-of-datastore;contextroot",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 384523968,
              "status": "passed"
            },
            "line": 5,
            "name": "the datastore application is alive",
            "match": {
              "location": "ContextRootStepDefinition.groovy:8"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 77671148,
              "status": "passed"
            },
            "line": 6,
            "name": "I navigate to \\"http://datastore.trevorism.com\\"",
            "match": {
              "arguments": [
                {
                  "val": "http://datastore.trevorism.com",
                  "offset": 15
                }
              ],
              "location": "ContextRootStepDefinition.groovy:12"
            },
            "keyword": "When "
          },
          {
            "result": {
              "duration": 14576159,
              "status": "passed"
            },
            "line": 7,
            "name": "the API returns an array, letting me know where I can go next",
            "match": {
              "location": "ContextRootStepDefinition.groovy:20"
            },
            "keyword": "Then "
          }
        ]
      },
      {
        "line": 9,
        "name": "ContextRoot on app engine",
        "description": "",
        "id": "context-root-of-datastore;contextroot-on-app-engine",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 68537583,
              "status": "passed"
            },
            "line": 10,
            "name": "the datastore application is alive",
            "match": {
              "location": "ContextRootStepDefinition.groovy:8"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 551052678,
              "status": "passed"
            },
            "line": 11,
            "name": "I navigate to \\"https://trevorism-gcloud.appspot.com\\"",
            "match": {
              "arguments": [
                {
                  "val": "https://trevorism-gcloud.appspot.com",
                  "offset": 15
                }
              ],
              "location": "ContextRootStepDefinition.groovy:12"
            },
            "keyword": "When "
          },
          {
            "result": {
              "duration": 114348,
              "status": "passed"
            },
            "line": 12,
            "name": "the API returns an array, letting me know where I can go next",
            "match": {
              "location": "ContextRootStepDefinition.groovy:20"
            },
            "keyword": "Then "
          }
        ]
      },
      {
        "line": 14,
        "name": "Ping",
        "description": "",
        "id": "context-root-of-datastore;ping",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 69567847,
              "status": "passed"
            },
            "line": 15,
            "name": "the datastore application is alive",
            "match": {
              "location": "ContextRootStepDefinition.groovy:8"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 134903419,
              "status": "passed"
            },
            "line": 16,
            "name": "I navigate to /ping on \\"http://datastore.trevorism.com\\"",
            "match": {
              "arguments": [
                {
                  "val": "http://datastore.trevorism.com",
                  "offset": 24
                }
              ],
              "location": "ContextRootStepDefinition.groovy:16"
            },
            "keyword": "When "
          },
          {
            "result": {
              "duration": 9590387,
              "error_message": "Assertion failed:",
              "status": "failed"
            },
            "line": 17,
            "name": "pong is returned, to indicate the service is alive",
            "match": {
              "location": "ContextRootStepDefinition.groovy:25"
            },
            "keyword": "Then "
          }
        ]
      },
      {
        "line": 19,
        "name": "Ping on app engine",
        "description": "",
        "id": "context-root-of-datastore;ping-on-app-engine",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 99346798,
              "status": "passed"
            },
            "line": 20,
            "name": "the datastore application is alive",
            "match": {
              "location": "ContextRootStepDefinition.groovy:8"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 50802650,
              "status": "passed"
            },
            "line": 21,
            "name": "I navigate to /ping on \\"https://trevorism-gcloud.appspot.com\\"",
            "match": {
              "arguments": [
                {
                  "val": "https://trevorism-gcloud.appspot.com",
                  "offset": 24
                }
              ],
              "location": "ContextRootStepDefinition.groovy:16"
            },
            "keyword": "When "
          },
          {
            "result": {
              "duration": 587951,
              "error_message": "Assertion failed:",
              "status": "failed"
            },
            "line": 22,
            "name": "pong is returned, to indicate the service is alive",
            "match": {
              "location": "ContextRootStepDefinition.groovy:25"
            },
            "keyword": "Then "
          }
        ]
      }
    ],
    "name": "Context Root of Datastore",
    "description": "In order to use the datastore API, it must be available",
    "id": "context-root-of-datastore",
    "keyword": "Feature",
    "uri": "features/ContextRoot.feature"
  }
]"""
    }
}
