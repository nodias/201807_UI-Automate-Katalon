import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

response = WS.sendRequest(findTestObject('zapi/getVersionStatus'))

def arrayVersions = response.responseText

def list = new JsonSlurper().parseText(arrayVersions)

def listValues = list.values

for (var in listValues) {
	def date1 = Date.parse('yyyy-MM-dd', var.startDate)
	def date2 = Date.parse('yyyy-MM-dd', var.releaseDate)
	def date3 = new Date()
	println date1, date2, date3
	
}