#!groovy

import hudson.model.*
import jenkins.model.*
import jenkins.security.*
import jenkins.security.apitoken.*

def userName = "jenkins"
def tokenName = "jenkins"

def user = User.get(userName, false)
def apiTokenProperty = user.getProperty(ApiTokenProperty.class)
def result = apiTokenProperty.tokenStore.generateNewToken(tokenName)
File file = new File("/var/lib/jenkins/token")
file.append(result.plainValue)
user.save()