#!groovy

import hudson.model.*
import hudson.security.*
import hudson.security.csrf.DefaultCrumbIssuer
import jenkins.model.*
import jenkins.security.apitoken.*

def instance = Jenkins.getInstance()
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
def users = hudsonRealm.getAllUsers()
def userName = "jenkins"
def password = "jenkins"
def tokenName = "jenkins"

instance.setCrumbIssuer(new DefaultCrumbIssuer(true))
instance.save()

users_s = users.collect { it.toString() }
def userExists = "jenkins" in users_s

if (!userExists) {
    hudsonRealm.createAccount(userName, password)
    instance.setSecurityRealm(hudsonRealm)
    def strategy = new hudson.security.FullControlOnceLoggedInAuthorizationStrategy()
    strategy.setAllowAnonymousRead(false)
    instance.setAuthorizationStrategy(strategy)
    instance.save()
    
    def user = User.get(userName, false)
    def apiTokenProperty = user.getProperty(ApiTokenProperty.class)
    def result = apiTokenProperty.tokenStore.generateNewToken(tokenName)
    user.save()

    File file = new File("/var/lib/jenkins/token")
    file.append(result.plainValue)
}