#!groovy
import hudson.security.*
import jenkins.model.*
import hudson.security.csrf.DefaultCrumbIssuer

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
}