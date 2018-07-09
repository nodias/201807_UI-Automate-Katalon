import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

response = WS.sendRequest(findTestObject('zapi/getVersionStatus'))

def arrayVersions = response.responseText

def list = new JsonSlurper().parseText(arrayVersions)

def listValues = list.values

def versions = []

for (def var : listValues) {
    def date1 = Date.parse('yyyy-MM-dd', var.startDate)

    def date2 = Date.parse('yyyy-MM-dd', var.releaseDate)

    def date3 = new Date()

    if ((date3 > date1) || (date3 < date2)) {
        versions.add(var.id)
    }
}

def keys = ['QAC-3', 'QAC-4']

for (def var : keys) {
    println(var) //	WebUI.callTestCase(findTestCase('Main Test Cases/'+var), [:],
    //		FailureHandling.STOP_ON_FAILURE)

    println(GlobalVariable.phase)
}

