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

// project id를 이용해 versionStatus 가져옴.
response = WS.sendRequest(findTestObject('zapi/getVersionStatus'))

// Parsing
def arrayVersions = response.responseText

def list = new JsonSlurper().parseText(arrayVersions)

def listValues = list.values

def versions = []

//jira issue 대체 (원래는 아래 loop안에서 version과 phase정보를 이용해 jira에서 해당하는 TC들을 모두 불러옵니다.)
def kr_dv = ['QAC-3', 'QAC-4']
def kr_st = ['QAC-3']

// version들 중 현재 sprint에 맞는 version을 선별 한 후, 세부 규칙에 따른 phase의 TC실행
for (def var : listValues) {
    def date1 = Date.parse('yyyy-MM-dd', var.startDate)

    def date2 = Date.parse('yyyy-MM-dd', var.releaseDate)

    def date3 = new Date()

    if ((date3 > date1) || (date3 < date2)) {
        versions.add(var.id)
		
		// version별 세부 규칙에 해당 하는 phase의 TC들을 불러오는 api가 있어야 하지만 kr_dv배열로 대체
		 
        for (def var2 : kr_dv) {
            WebUI.callTestCase(findTestCase('Main Test Cases/' + var2), [:], FailureHandling.STOP_ON_FAILURE)
        }
    }
}